package com.example.java_story_bk.models.bodyModel;

public class SendLoginBody {
    String email, password;

    public void setEmail(String email) {
        this.email = email;
    }

    public SendLoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
