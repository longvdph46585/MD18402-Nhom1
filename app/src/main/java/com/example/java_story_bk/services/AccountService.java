package com.example.java_story_bk.services;


import android.content.Context;
import android.content.SharedPreferences;

public class AccountService {
    // Kiểm tra userLogin ?
    Context context;
    SharedPreferences account;

    public AccountService(Context context) {
        this.context = context;
        account = context.getSharedPreferences("ACCOUNT", context.MODE_PRIVATE);

    }


    public boolean  checkLoginAccount() {

        return account.contains("email");
    }

}
