package com.desafiosantander.Home;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.desafiosantander.LoggedUser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

interface HomeInteractorInput {
    public void validadeUser(EditText user);
    public void validadePassword(EditText Password);
    public void doLogin(String user, String password);
    public void getResponse(Boolean success, String response);
}


public class HomeInteractor implements HomeInteractorInput {

    public static String TAG = HomeInteractor.class.getSimpleName();
    public HomePresenterInput output;
    public HomeWorkerInput aHomeWorkerInput;
    public EditText userEditText;
    public EditText passwordEditText;
    public LoggedUser loggedUser;

    public HomeWorkerInput getHomeWorkerInput() {
        if (aHomeWorkerInput == null) return new HomeWorker();
        return aHomeWorkerInput;
    }

    public void setHomeWorkerInput(HomeWorkerInput aHomeWorkerInput) {
        this.aHomeWorkerInput = aHomeWorkerInput;
    }

    @Override
    public void validadeUser(EditText user) {
        userEditText = user;
        userEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                output.validadeUser(userEditText.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    public void validadePassword(EditText Password) {
        passwordEditText = Password;
        passwordEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                output.validadePassword(passwordEditText.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    public void doLogin(String user, String password) {
        Map<String, String> postData = new HashMap<>();
        postData.put("user", user);
        postData.put("password", password);
        HomeRequest task = new HomeRequest(postData, this);
        task.execute("https://bank-app-test.herokuapp.com/api/login");
    }

    @Override
    public void getResponse(Boolean success, String response) {
        Log.d("RESPONSE: ","Success: "+success+" REsponse: "+response);
        try{
            JSONObject obj = new JSONObject(response);
            JSONObject user = new JSONObject(obj.getString("userAccount"));
            loggedUser = new LoggedUser(
                    user.getString("userId"),
                    user.getString("name"),
                    user.getString("bankAccount"),
                    user.getString("agency"),
                    user.getString("balance")
            );
            output.login(success, loggedUser);


        }catch (Exception e){

        }
    }
}
