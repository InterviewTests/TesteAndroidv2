package br.com.giovanni.testebank.Presenter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.giovanni.testebank.Activity.IPresenterActivity;
import br.com.giovanni.testebank.Model.Transacao;


public class PresenterDetail implements IDetailPresenter {

    private IPresenterActivity iPresenterActivity;
    private ArrayList<Transacao> newTransacao = new ArrayList<>();

    public void newList(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray array = jsonObject.getJSONArray("statementList");
            JSONObject jsonObjectError = new JSONObject(response).getJSONObject("error");

            if (jsonObjectError.toString().contentEquals("{}") ){
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    Transacao item = new Transacao(
                            object.getString("title"),
                            object.getString("desc"),
                            object.getDouble("value"),
                            object.getString("date")

                    );
                    this.newTransacao.add(item);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public PresenterDetail(IPresenterActivity activity) {
        this.iPresenterActivity = activity;
    }

    @Override
    public void intentDetail(String getRepost) {
        newTransacao.clear();
        newList(getRepost);
        iPresenterActivity.getResponse(newTransacao);
    }
}
