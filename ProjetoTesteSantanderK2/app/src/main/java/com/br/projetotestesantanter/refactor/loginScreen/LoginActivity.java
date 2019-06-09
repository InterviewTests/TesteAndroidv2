package com.br.projetotestesantanter.refactor.loginScreen;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.br.projetotestesantanter.R;
import com.br.projetotestesantanter.refactor.loginScreen.LoginModel.LoginRequest;

interface LoginActivityInput {
    void displayHomeMetaData(LoginModel.Login response);
    void displayMensagem(String mensage);
    void showProgressBar();
    void hideProgressBar();
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

   protected LoginInteractorInput output;
   protected LoginRouter router;

   @BindView(R.id.edt_user)
   EditText edtUser;

   @BindView(R.id.edt_password)
   EditText edtPassword;

   @BindView(R.id.progressBar)
   ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginConfigurator.INSTANCE.configure(this);
    }

    @Override
    public void displayHomeMetaData(LoginModel.Login response) {

        router.statementScreen(response);
    }

    @Override
    public void displayMensagem(String mensage) {
        Snackbar.make(findViewById(android.R.id.content),
                mensage,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnLogar)
    public void prepareRequest() {

        LoginRequest request = new  LoginRequest();
        request.login = edtUser.getText().toString();
        request.password = edtPassword.getText().toString();
        output.fetchHomeMetaData(request);
    }
}
