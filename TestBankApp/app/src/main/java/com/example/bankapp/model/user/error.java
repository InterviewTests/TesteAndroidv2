package com.example.bankapp.model.user;

import java.io.Serializable;

public class Error implements Serializable {

    private long code;
    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
