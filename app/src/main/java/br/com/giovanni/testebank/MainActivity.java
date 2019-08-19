package br.com.giovanni.testebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import br.com.giovanni.testebank.Presenter.DetailActivity;
import br.com.giovanni.testebank.Presenter.LoginInteractor;
import br.com.giovanni.testebank.Presenter.PresenterLogin;

public class MainActivity extends AppCompatActivity implements SetItentPrincipal {

    public EditText getUser;
    public EditText getPassword;
    public Button btnLogin;
    public TextView textViewJson;
    private String getUserConvert;
    private String getPasswordConvert;
    private LoginInteractor loginInteractor;
    private PresenterLogin presenterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getUser = findViewById(R.id.editTextUserId);
        getPassword = findViewById(R.id.editTextPasswordId);
        btnLogin = findViewById(R.id.buttonLoginId);
        textViewJson = findViewById(R.id.txtNameId);
        presenterLogin = new PresenterLogin(this);
        loginInteractor = new LoginInteractor(presenterLogin);

        btnLoginOnClick();

    }

    public void btnLoginOnClick (){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUserConvert = getUser.getText().toString();
                getPasswordConvert = getPassword.getText().toString().trim();

                loginInteractor.getLoginAcces(getUserConvert,getPasswordConvert);

            }
        });
    }


    @Override
    public void intentPrincipal (){
        Intent setIntent = new Intent(this, DetailActivity.class);
        System.out.println("Parou no intent");
        startActivity(setIntent);
    }



}