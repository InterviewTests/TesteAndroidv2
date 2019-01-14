package br.com.accenture.santander.wallacebaldenebre.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import br.com.accenture.santander.wallacebaldenebre.R;
import br.com.accenture.santander.wallacebaldenebre.ui.account.AccountActivity;
import br.com.accenture.santander.wallacebaldenebre.utils.Helper;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginPresenter p;
    private ProgressDialog pd;
    private EditText edtUser, edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUp();
    }

    @Override
    public void makeLogin() {
        p.makeLogin(this, edtUser.getText().toString().trim(), edtPassword.getText().toString().trim());
        Helper.openClass(this, AccountActivity.class);
    }

    @Override
    public void setUp() {
        p = new LoginPresenter();
        p.onAttach(this);

        edtUser = findViewById(R.id.edt_login_user);
        edtPassword = findViewById(R.id.edt_login_password);
    }

    public void onLogin(View view) {
        if (edtUser.getText().toString().trim().equals("test_user") &&
                edtPassword.getText().toString().trim().equals("Test@1")) {
            makeLogin();
        } else {
            Snackbar.make(view, "Usuário/Senha inválidos", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pd = new ProgressDialog(LoginActivity.this);
                pd.setTitle("Aguarde...");
                pd.setMessage("Carregando os dados...");
                pd.setCancelable(true);
                pd.show();

                Handler hpd = new Handler();
                hpd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void hideProgress() {
        if (pd.isShowing()) pd.dismiss();
    }

    @Override
    public void hideKeyboard() {

    }
}
