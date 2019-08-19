package br.com.giovanni.testebank.Presenter;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.giovanni.testebank.SetItentPrincipal;
import br.com.giovanni.testebank.UserAccount;

public class PresenterLogin implements PresenterLoginImput {

    public SetItentPrincipal activity;

    public PresenterLogin (SetItentPrincipal activity){
        this.activity = activity;
    }

    @Override
    public void presenterLogin(String resposta) {

        try {
            JSONObject jsonObjectUser = new JSONObject(resposta).getJSONObject("userAccount");
            JSONObject jsonObjectError = new JSONObject(resposta).getJSONObject("error");

            if (jsonObjectError.toString().contentEquals("{}") ){
                UserAccount user = new UserAccount(jsonObjectUser);
               this.activity.changeScreen(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
