package com.accenture.project.apptesteandroid.login;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.accenture.project.apptesteandroid.R;


interface ILoginActivity {

    void displayMessageToUser(String message);

    void displayLastUserLogged(String login);

    void resetPasswordField();
}

public class LoginActivity extends AppCompatActivity implements ILoginActivity {

    private Button btnLogin;
    private EditText edtUser, edtPassword;
    private ProgressBar progressBar;
    LoginRouter router;
    LoginInteractor loginInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginConfigurator.GET_INSTANCE.configure(this);
        initViews();
        fetchLastUserLogged();

    }


    private void initViews() {

        btnLogin = findViewById(R.id.btn_login);
        edtUser = findViewById(R.id.edt_user);
        edtPassword = findViewById(R.id.edt_password);
        progressBar = findViewById(R.id.progress_bar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.GONE);
                loginInteractor.login(edtUser.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    private void fetchLastUserLogged(){
        loginInteractor.fetchLastUserLogged();
    }

    @Override
    public void displayMessageToUser(String message) {
        progressBar.setVisibility(View.GONE);
        btnLogin.setVisibility(View.VISIBLE);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Atenção");
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    @Override
    public void displayLastUserLogged(String login) {
        edtUser.setText(login);
    }

    @Override
    public void resetPasswordField() {
        edtPassword.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
        btnLogin.setVisibility(View.VISIBLE);
    }
}
