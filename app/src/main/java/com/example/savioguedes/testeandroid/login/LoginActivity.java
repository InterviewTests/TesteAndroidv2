package com.example.savioguedes.testeandroid.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.savioguedes.testeandroid.R;
import com.example.savioguedes.testeandroid.StateActivity;
import com.example.savioguedes.testeandroid.model.Login;
import com.example.savioguedes.testeandroid.model.UserAccount;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    LoginPresenter loginPresenter;
    UserAccount userAccount;
    Login login;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = new Login();
        userAccount = new UserAccount();
        loginPresenter = new LoginPresenter(this, getParent());
    }

    @Override
    public void initView() {

        btnlogin = findViewById(R.id.login_button_login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login.setUser("test_user");
                login.setPassword("Test@1");
                loginPresenter.getLoginExecute(login);

                Intent intent = new Intent(LoginActivity.this, StateActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void passUserinfo() {

    }

}
