package com.example.java_story_bk.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.java_story_bk.MainActivity;
import com.example.java_story_bk.R;
import com.example.java_story_bk.models.Account;
import com.example.java_story_bk.screens.LoginActivity;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.MainServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    private AccountService accountService;
    private RelativeLayout wrapUserInfoFragment_login, wrapUserInfoFragment;
    private TextView wrapUserInfoFragment_logout, wrapUserInfoFragment_lvl, wrapUserInfoFragment_name, wrapUserInfoFragment_email, wrapUserInfoFragment_money;
    private View view;
    private Button loginInFragmentAccount;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("fragment", "AccountFragment first load");
    }

    @Override
    public void onResume() {
        super.onResume();
        updatePage();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("fragment", "AccountFragment onCreateView");

        view = inflater.inflate(R.layout.fragment_account, container, false);
        wrapUserInfoFragment_login = view.findViewById(R.id.wrapUserInfoFragment_login);
        wrapUserInfoFragment = view.findViewById(R.id.wrapUserInfoFragment);
        loginInFragmentAccount = view.findViewById(R.id.loginInFragmentAccount);

        wrapUserInfoFragment_logout = view.findViewById(R.id.wrapUserInfoFragment_logout);
        accountService = new AccountService(getContext());

        wrapUserInfoFragment_lvl = view.findViewById(R.id.wrapUserInfoFragment_lvl);
        wrapUserInfoFragment_name = view.findViewById(R.id.wrapUserInfoFragment_name);
        wrapUserInfoFragment_email = view.findViewById(R.id.wrapUserInfoFragment_email);
        wrapUserInfoFragment_money = view.findViewById(R.id.wrapUserInfoFragment_money);

        loginInFragmentAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        wrapUserInfoFragment_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountService.logoutAction();
                updatePage();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void updatePage() {

        if (!accountService.getAccountID().isEmpty()) {
            wrapUserInfoFragment.setVisibility(View.VISIBLE);
            wrapUserInfoFragment_login.setVisibility(View.GONE);
            AccountService.HandelCallBackResultRefresh callBackResult = new AccountService.HandelCallBackResultRefresh() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResult(Account data) {
                    wrapUserInfoFragment_lvl.setText("Cấp độ " +data.getLevel());
                    wrapUserInfoFragment_name.setText(data.getName());
                    wrapUserInfoFragment_email.setText("Email: " +data.getEmail());
                    wrapUserInfoFragment_money.setText( "Linh thạch: " +data.getSpirit_stone());
                }

                @Override
                public void onFailure(String err) {

                }
            };
            accountService.refreshAccountLocal(callBackResult);

        } else {
            wrapUserInfoFragment.setVisibility(View.GONE);
            wrapUserInfoFragment_login.setVisibility(View.VISIBLE);
        }
    }
}