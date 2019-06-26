package com.example.santanderapp.santander.homeScreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.santanderapp.santander.R;
import com.example.santanderapp.santander.homeScreen.presenter.ILoginPresenter;
import com.example.santanderapp.santander.homeScreen.presenter.LoginPresenter;
import com.example.santanderapp.santander.util.Utils;

public class LoginActivity extends AppCompatActivity implements ILoginActivity {

    private Button btnLogin;
    private EditText edtUser;
    private EditText edtPassword;
    private ProgressDialog progress;

    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configureUI();

        loginPresenter = new LoginPresenter(this, this);

        edtUser.setText(loginPresenter.verifyHasSavedLogin());

        btnLogin.setOnClickListener(listenerLogin);

    }

    private void configureUI() {
        btnLogin = findViewById(R.id.btnLogin);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }

    //Valida se no campo user é um CPF ou e-mail válido e o campo password constam as regras mínimas
    public static boolean validatesData(String edtUser, String edtPassword) {
        if ((!Utils.isEmailValid(edtUser)) && (!Utils.isCpfValid(edtUser))) {
            return false;
        }
        if (!Utils.isPasswordValid(edtPassword)) {
            return false;
        }
        return true;
    }

    //Evento de onclick do botão Login
    private View.OnClickListener listenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progress = new ProgressDialog(LoginActivity.this);
            progress.setTitle(getString(R.string.loading));
            progress.setMessage(getString(R.string.whaitLoading));
            progress.setCancelable(false);
            progress.show();
            loginPresenter.callAPI(edtUser.getText().toString(), edtPassword.getText().toString());

        }
    };

    @Override
    public void closeProgress() {
        progress.dismiss();
    }
}


