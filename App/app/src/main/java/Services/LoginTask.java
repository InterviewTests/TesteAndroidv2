package Services;

import android.os.AsyncTask;

import Presenters.LoginPresenterInput;

public class LoginTask extends AsyncTask {
    private String json;
    private String resposta;
    private LoginResponse client;
    private LoginPresenterInput presenter;

    public LoginTask(LoginPresenterInput presenter){
        this.presenter = presenter;
    }

    public void setParametros(String json) {
        this.json = json;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        client = new LoginResponse();
        resposta = client.get(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        presenter.injetarDependencia(resposta);
    }
}
