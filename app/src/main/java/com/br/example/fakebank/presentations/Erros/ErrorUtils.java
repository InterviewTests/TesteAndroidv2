package com.br.example.fakebank.presentations.Erros;

import android.content.Context;

public class ErrorUtils extends RuntimeException {
    private Integer resourceMessage;

    ErrorUtils(Integer resourceMessage) {
        super();
        this.resourceMessage = resourceMessage;
    }

    public ErrorUtils(String message) {
        super(message);
    }

    public String returnMessageByContext(Context context){
        return context.getString(resourceMessage);
    }
}
