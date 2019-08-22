package br.com.giovanni.testebank.Helpers;

import android.os.AsyncTask;

import br.com.giovanni.testebank.Services.LoginWebService;
import br.com.giovanni.testebank.Presenter.IPresenterLogin;

public class LoginTask extends AsyncTask {

    public LoginWebService client;
    private String json;
    private String resposta;
    public IPresenterLogin presenterLoginImput;

    public LoginTask (IPresenterLogin presenterLoginImput){
        this.presenterLoginImput = presenterLoginImput;
    }

    public void setParametros(String json){
        this.json = json;
    }
    @Override
    protected String doInBackground(Object[] objects) {
        client = new LoginWebService();
        resposta = client.post(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        presenterLoginImput.presenterLogin(resposta);
        System.out.println(resposta);
    }

}