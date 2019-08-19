package br.com.giovanni.testebank.Presenter;

import android.os.AsyncTask;

import br.com.giovanni.testebank.LoginWebService;

public class LoginTask extends AsyncTask {

    public LoginWebService client;
    private String json;
    private String resposta;
    public PresenterLoginImput presenterLoginImput;


    public LoginTask (PresenterLoginImput presenterLoginImput){
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
        presenterLoginImput.presenterLogin();
        System.out.println(resposta);
    }


}
