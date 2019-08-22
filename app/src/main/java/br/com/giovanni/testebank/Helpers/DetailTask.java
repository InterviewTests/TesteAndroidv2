package br.com.giovanni.testebank.Helpers;

import android.os.AsyncTask;

import br.com.giovanni.testebank.Presenter.IDetailPresenter;
import br.com.giovanni.testebank.Services.StatementsWebService;


public class DetailTask extends AsyncTask {

    public StatementsWebService client;
    private String resposta;
    private int userId;
    private IDetailPresenter iDetailPresenter;

    public DetailTask(IDetailPresenter iDetailPresenter) {
        this.iDetailPresenter = iDetailPresenter;
    }

    public void setUserId(int idUser) {
        this.userId = idUser;

    }

    @Override
    protected String doInBackground(Object[] objects) {
        client = new StatementsWebService();
        resposta = client.get(userId);

        return resposta;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        System.out.println(resposta);

        iDetailPresenter.intentDetail(resposta);
    }
}