//package br.com.giovanni.testebank.Presenter;
//
//import android.os.AsyncTask;
//
//import br.com.giovanni.testebank.StatementsWebService;
//
//
//public class StatementsTask extends AsyncTask {
//
//    public StatementsWebService client;
//    private String resposta;
//
//    public setUserid (int idUser){
//
//    }
//
//    @Override
//    protected String doInBackground(Object[] objects) {
//        client = new StatementsWebService();
//        resposta = client.post(json);
//        return resposta;
//    }
//
//    @Override
//    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);
//        System.out.println(resposta);
//    }
//}
