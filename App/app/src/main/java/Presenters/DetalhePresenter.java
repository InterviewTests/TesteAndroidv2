package Presenters;

import com.example.testesantander.DetalheActivityInput;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Models.Detalhe;

public class DetalhePresenter implements DetalhePresenterInput {
    private ArrayList<Detalhe> listaDetalhes;
    private DetalheActivityInput activity;

    public DetalhePresenter(DetalheActivityInput activity){
        this.activity = activity;
    }

    @Override
    public void setResposta(String msg){
        listaDetalhes = new ArrayList<>();
        criaObjetos(msg);
        criaListaDetalhes();
    }

    private void criaObjetos(String resposta){
        try {
            JSONArray statementList = new JSONObject(resposta).getJSONArray("statementList");
            JSONObject error = new JSONObject(resposta).getJSONObject("error");
            activity.sendMessage(statementList.getJSONObject(0).getString("title"));
            if (error.toString().contentEquals("{}")) {
                JSONObject obj;
                Detalhe det;
                for(int i = 0; i < statementList.length(); i++){
                    obj = statementList.getJSONObject(i);
                    det = new Detalhe(obj.getString("title"), obj.getString("desc"),
                            obj.getString("date"), obj.getDouble("value"));
                    listaDetalhes.add(det);
                }
            } else
                activity.sendMessage(error.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void criaListaDetalhes(){
        activity.injetarDependencia(listaDetalhes);
    }
}
