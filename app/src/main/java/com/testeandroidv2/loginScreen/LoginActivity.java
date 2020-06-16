package com.testeandroidv2.loginScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.testeandroidv2.R;


interface LoginActivityInput {
    void displayLoginData(LoginViewModel viewModel);
    void displayLoginErro(Object erro);
}


public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;
    LoginModel login;

    private EditText edtUser;
    private EditText edtPassword;
    private TextView loginMessageErro;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        edtUser = findViewById(R.id.edtUserLoginScreen);
        edtPassword = findViewById(R.id.edtPasswordLoginScreen);
        btnLogin = findViewById(R.id.btnLogin);
        loginMessageErro = findViewById(R.id.loginMessageErro);
        loginMessageErro.setVisibility(View.GONE);

        btnLogin.setOnClickListener(login());

        LoginConfigurator.INSTANCE.configure(this);
    }

    private View.OnClickListener login(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginMessageErro.setVisibility(View.GONE);
                btnLogin.setText(getString(R.string.loginScreenBtnWaitMessage));
                fetchMetaData();
            }
        };
    }

    public void fetchMetaData(){
        LoginRequest aLoginRequest = new LoginRequest();
        aLoginRequest.user = (edtUser != null) ? edtUser.getText().toString(): null;
        aLoginRequest.password = (edtPassword != null) ? edtPassword.getText().toString(): null;
        output.fetchLoginData(aLoginRequest);
    }

    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + new Gson().toJson(viewModel) + "]");
        // Deal with the data
    }

    @Override
    public void displayLoginErro(Object erro){
        String erroMessage;
        if(erro instanceof LinkedTreeMap){
            erroMessage = erro.toString();
        }else{
            erroMessage = erro.toString();
        }
        loginMessageErro.setText(erroMessage);
        loginMessageErro.setVisibility(View.VISIBLE);
    }
}
