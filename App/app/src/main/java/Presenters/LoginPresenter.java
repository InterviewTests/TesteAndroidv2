package Presenters;

import com.example.testesantander.LoginActivityInput;

import org.json.JSONException;
import org.json.JSONObject;

import Models.UserAccount;


public class LoginPresenter implements LoginPresenterInput {
    public LoginActivityInput activity;

    public LoginPresenter(LoginActivityInput activity){
        this.activity = activity;
    }

    public void setUserAccount(String resposta) {
        activity.stopLoadingBar();
        try {
            JSONObject userAccount = new JSONObject(resposta).getJSONObject("userAccount");
            JSONObject error = new JSONObject(resposta).getJSONObject("error");
            if (error.toString().contentEquals("{}")) {
                activity.iniciarDetailActivity(new UserAccount(userAccount));
            } else
                activity.injetarDependencia(error.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void injetarDependencia(String resposta) {
        setUserAccount(resposta);
    }
}