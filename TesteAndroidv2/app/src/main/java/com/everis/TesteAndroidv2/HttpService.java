package com.everis.TesteAndroidv2;

import android.os.AsyncTask;

public class HttpService extends AsyncTask<Void,Void,Credenciais> {

    private final String USER;
    private final String PASSWORD;

    public HttpService(String user, String password){
        this.USER = user;
        this.PASSWORD = password;
    }

    @Override
    protected Credenciais doInBackground(Void... voids) {
        return null;
    }
}
