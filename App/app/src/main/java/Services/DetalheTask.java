package Services;

import android.os.AsyncTask;

import Presenters.DetalhePresenterInput;

public class DetalheTask extends AsyncTask {
    private DetalhePresenterInput presenter;
    private String resposta;
    private int userId;

    public DetalheTask(DetalhePresenterInput presenter){
        this.presenter = presenter;
    }

    public void setParametros(int userId){
        this.userId = userId;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        DetalheWebClient client = new DetalheWebClient();
        resposta = client.get(userId);
        return resposta;
    }

    @Override
    protected void onPostExecute(Object o){
        super.onPostExecute(o);
        presenter.setResposta(resposta);
    }
}
