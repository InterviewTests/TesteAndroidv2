package com.br.projetotestesantanter.refactor.loginScreen;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.br.projetotestesantanter.refactor.loginScreen.LoginModel.LoginRequest;

interface LoginActivityInput {
    void displayHomeMetaData(LoginModel.LoginResponse response);
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    LoginInteractorInput output;
    LoginRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginConfigurator.INSTANCE.configure(this);

    }

    @Override
    public void displayHomeMetaData(LoginModel.LoginResponse response) {

        router.statementScreen(response);
    }

    private void prepareRequest() {

        LoginRequest request = new  LoginRequest();
        request.login = "teste";
        request.password = "12334";
        output.fetchHomeMetaData(request);
    }
}
