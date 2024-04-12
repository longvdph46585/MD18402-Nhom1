package com.example.java_story_bk.screens;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.Account;
import com.example.java_story_bk.models.ChapterInfo;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.MainServices;
import com.example.java_story_bk.untils.Helpers;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tvLogin_SignUp;
    Button btnLogin;
    TextInputLayout et_LoginUsername, et_Password;
    AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        accountService = new AccountService(LoginActivity.this);

        et_LoginUsername = findViewById(R.id.et_LoginUsername);
        et_Password = findViewById(R.id.et_LoginPassword);

        tvLogin_SignUp = findViewById(R.id.tvLogin_SignUp);
        btnLogin = findViewById(R.id.btnLogin);
        AccountService.HandelCallBackResultLogin callBackLogin = new AccountService.HandelCallBackResultLogin() {
            @Override
            public void onResult(Account data) {
                MainServices.storyService.getCheckNeedSynchronized(Helpers.getDeviceUUID(LoginActivity.this),data.get_id()).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body() == true) {
                            showSyncDialog(data);
                        } else {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });

                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(String err) {
                if (err.contains("Account")) {
                    et_LoginUsername.setError("Tài khoản không tồn tại");
                } else if (err.contains("Password")) {
                    et_Password.setError("Sai mật khẩu");
                }

            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = et_LoginUsername.getEditText().getText().toString();
                String textPassword = et_Password.getEditText().getText().toString();
                if (checkValid(textEmail, textPassword)) {
                    accountService.loginAction(textEmail, textPassword, callBackLogin);

                }
            }
        });

        tvLogin_SignUp.setOnClickListener(view -> {

            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

    }
    private boolean checkValid(String email, String password) {


        if (!Helpers.isValidEmail(email)) {

            et_LoginUsername.setError("Email không đúng định dạng");
            return false;
        } else {
            et_LoginUsername.setError("");
        }
        if (password.length() < 6) {
            et_Password.setError("Mật khẩu phải có tối thiểu 6 ký tự");
            return false;
        } else {
            et_Password.setError("");
        }
        return true;

    }
    private void showSyncDialog(Account accountData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lịch sử đọc");
        builder.setMessage("Bạn có muốn đồng bộ lịch sử đọc vào tài khoản này!");

        builder.setPositiveButton("Đồng bộ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "Oke"

                    MainServices.storyService.makeSynchronizedForUser(Helpers.getDeviceUUID(LoginActivity.this),accountData.get_id(), true).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            finish();
                            dialog.dismiss();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });


            }
        });

        builder.setNegativeButton("Xoá lịch sử đọc", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "Huỷ"
                    MainServices.storyService.makeSynchronizedForUser(Helpers.getDeviceUUID(LoginActivity.this),accountData.get_id(), false).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            finish();
                            dialog.dismiss();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}