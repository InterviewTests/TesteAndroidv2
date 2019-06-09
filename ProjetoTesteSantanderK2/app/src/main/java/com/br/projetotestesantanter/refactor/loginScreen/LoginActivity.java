package com.br.projetotestesantanter.refactor.loginScreen;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.br.projetotestesantanter.R;
import com.br.projetotestesantanter.refactor.loginScreen.LoginModel.LoginRequest;

interface LoginActivityInput {
    void displayHomeMetaData(LoginModel.LoginResponse response);
    void displayMensagem(String mensage);
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

   protected LoginInteractorInput output;
   protected LoginRouter router;

   @BindView(R.id.edt_user)
   EditText edtUser;

   @BindView(R.id.edt_password)
   EditText edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginConfigurator.INSTANCE.configure(this);
    }

    @Override
    public void displayHomeMetaData(LoginModel.LoginResponse response) {

        router.statementScreen(response);
    }

    @Override
    public void displayMensagem(String mensage) {
        Snackbar.make(findViewById(android.R.id.content),
                mensage,Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnLogar)
    public void prepareRequest() {

        LoginRequest request = new  LoginRequest();
        request.login = edtUser.getText().toString();
        request.password = edtPassword.getText().toString();
        output.fetchHomeMetaData(request);
    }
}
