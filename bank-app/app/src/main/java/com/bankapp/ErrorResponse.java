package com.bankapp;

import androidx.annotation.NonNull;

public class ErrorResponse {
    private long code;
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return message;
    }
}
