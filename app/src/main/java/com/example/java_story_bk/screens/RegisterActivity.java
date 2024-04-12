package com.example.java_story_bk.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.Account;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.untils.Helpers;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout et_UsernameSignUp , et_PasswordSignUp , et_FullnessSignUp;
    Button btnSignUp;
    AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et_UsernameSignUp= findViewById(R.id.et_UsernameSignUp);
        et_PasswordSignUp = findViewById(R.id.et_passwordSignUp);
        et_FullnessSignUp = findViewById(R.id.et_FullnameSignUp);
        accountService = new AccountService(RegisterActivity.this);


        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_UsernameSignUp.getEditText().getText().toString();
                String pass  = et_PasswordSignUp.getEditText().getText().toString();
                String fullname = et_FullnessSignUp.getEditText().getText().toString();
                AccountService.HandelCallBackResultSignup resultSinup = new AccountService.HandelCallBackResultSignup() {
                    @Override
                    public void onResult(Account data) {

                        Toast.makeText(RegisterActivity.this, "Đăng Ký thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(String err) {

                        if(err.contains("account")) {
                            et_UsernameSignUp.setError("Tài khoản này đã tồn tại");
                        }
                        else if(err.contains("Name")) {
                            et_FullnessSignUp.setError("Tên này đã được sử dụng");
                        }

                    }
                };
                if (checkValid(email, pass,fullname)) {
                    accountService.signupAction(email, pass,fullname, resultSinup);

                }
            }
        });

    }
    private boolean checkValid(String email, String password, String fullname) {


        if (!Helpers.isValidEmail(email)) {

            et_UsernameSignUp.setError("Email không đúng định dạng");
            return false;
        } else {
            et_UsernameSignUp.setError("");
        }
        if (password.length() < 6) {
            et_PasswordSignUp.setError("Mật khẩu phải có tối thiểu 6 ký tự");
            return false;
        } else {
            et_PasswordSignUp.setError("");
        }
        if(fullname.length() <3) {
            et_FullnessSignUp.setError("Tên tài khoản phải có tối thiểu 3 ký tự");
        } else {
            et_FullnessSignUp.setError("");
        }

        return true;

    }
}