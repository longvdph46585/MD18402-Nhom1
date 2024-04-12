package com.example.java_story_bk.models;

import java.io.Serializable;

public class ErrorModel implements Serializable {
    String error;

    public ErrorModel(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
