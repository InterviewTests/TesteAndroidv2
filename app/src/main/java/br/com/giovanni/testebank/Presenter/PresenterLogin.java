package br.com.giovanni.testebank.Presenter;

import org.json.JSONException;
import org.json.JSONObject;
import br.com.giovanni.testebank.Activity.IItentLogin;
import br.com.giovanni.testebank.Model.UserAccount;

public class PresenterLogin implements IPresenterLogin {

    public IItentLogin activity;

    public PresenterLogin (IItentLogin activity){
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
            } else {
                this.activity.setMessage(jsonObjectError.getString("message"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}