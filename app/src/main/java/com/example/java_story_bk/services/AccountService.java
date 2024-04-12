package com.example.java_story_bk.services;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.java_story_bk.models.Account;
import com.example.java_story_bk.models.ErrorModel;
import com.example.java_story_bk.models.bodyModel.SendLoginBody;
import com.example.java_story_bk.models.bodyModel.SendSignupBody;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountService {
    // Kiểm tra userLogin ?
    Context context;
    SharedPreferences account;

    public AccountService(Context context) {
        this.context = context;
        account = context.getSharedPreferences("ACCOUNT", context.MODE_PRIVATE);
    }

    public SharedPreferences getAccountInfo() {
        return  account;
    }
    public boolean checkLoginAccount() {

        return account.contains("email");
    }
    public String getAccountID () {
        return  account.getString("_id", "");
    }
    public void loginAction(String email, String password, HandelCallBackResultLogin handelCallBackResultLogin) {
        MainServices.accountService.loginUserAction(new SendLoginBody(email, password)).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                final Account data = response.body();

                if (response.isSuccessful()) {
                    handelCallBackResultLogin.onResult(response.body());
                    SharedPreferences.Editor editor= account.edit();
                    editor.clear();
                    editor.putString("_id", data.get_id());
                    editor.putString("email", data.getEmail());
                    editor.apply();
                } else {
                    String errorMessage = null;
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        try {
                            errorMessage = errorBody.string();
                            JSONObject jsonError = new JSONObject(errorMessage);
                            String error = jsonError.getString("error");
                            handelCallBackResultLogin.onFailure(error);
                            // Xử lý thông báo lỗi
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                handelCallBackResultLogin.onFailure(t.getMessage());

            }
        });

    }
    public void signupAction(String email, String password, String fullName, HandelCallBackResultSignup HandelCallBackResultSignup) {
        MainServices.accountService.registerUserAction(new SendSignupBody(email, password, fullName)).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    HandelCallBackResultSignup.onResult(response.body());

                } else {
                    String errorMessage = null;
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        try {
                            errorMessage = errorBody.string();
                            JSONObject jsonError = new JSONObject(errorMessage);
                            String error = jsonError.getString("error");
                            HandelCallBackResultSignup.onFailure(error);
                            // Xử lý thông báo lỗi
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                HandelCallBackResultSignup.onFailure(t.getMessage());

            }
        });

    }

    public void logoutAction () {
        SharedPreferences.Editor editAccount = account.edit();
        editAccount.clear();
        editAccount.apply();
    }
    public void refreshAccountLocal(HandelCallBackResultRefresh callBack){
        MainServices.accountService.getInfoAccount(this.getAccountID()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                callBack.onResult(response.body());

            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                callBack.onFailure(t.toString());

            }
        });
    }


    public interface HandelCallBackResultSignup {
        void onResult(Account data);

        void onFailure(String err);
    }

    public interface HandelCallBackResultLogin {
        void onResult(Account data);

        void onFailure(String err);
    }
    public interface HandelCallBackResultRefresh {
        void onResult(Account data);

        void onFailure(String err);
    }
}
