package com.avanade.testesantander2;

import com.google.gson.annotations.Expose;

public class Erro {

    @Expose
    private int code;

    @Expose
    private String message;

    public Erro() {
    }

    public Erro(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Erro{" +
                "code=" + code +
                ",message='" + message + '\'' +
                '}';
    }
}


/*

{
    "userAccount": {},
    "error": {
        "code": 53,
        "message": "Usuário ou senha incorreta"
    }
}

 */