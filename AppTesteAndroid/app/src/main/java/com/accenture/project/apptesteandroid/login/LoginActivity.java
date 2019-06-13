package com.accenture.project.apptesteandroid.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.accenture.project.apptesteandroid.R;


interface ILoginActivity{

    void showMessageToUser(String message);
    void next();
}
public class LoginActivity extends AppCompatActivity implements ILoginActivity {

    private Button btnLogin;
    private LoginInteractor loginInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        loginInteractor = new LoginInteractor();
    }


    private void initViews(){

        Button btnLogin = findViewById(R.id.btn_login);
        final EditText edtUser = findViewById(R.id.edt_user);
        final EditText edtPassword = findViewById(R.id.edt_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginInteractor.login(edtUser.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    @Override
    public void showMessageToUser(String message) {

    }

    @Override
    public void next() {

    }
}
