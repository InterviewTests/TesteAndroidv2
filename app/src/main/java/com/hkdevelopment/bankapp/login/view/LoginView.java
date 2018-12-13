package com.hkdevelopment.bankapp.login.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hkdevelopment.bankapp.R;
import com.hkdevelopment.bankapp.login.presenter.LoginPresenter;
import com.hkdevelopment.bankapp.login.presenter.LoginPresenterInt;
import com.hkdevelopment.bankapp.utils.PreferencesManager;
import com.hkdevelopment.bankapp.utils.PreferencesManagerInt;

public class LoginView extends AppCompatActivity implements LoginViewInt{

    private LoginPresenterInt presenter;
    private PreferencesManagerInt prefs;
    private Button btnLogin;
    private EditText edtUser,edtPassword;
    private String user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        prefs=new PreferencesManager(this);
        presenter=new LoginPresenter(this,this,prefs);

        findViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=edtUser.getText().toString();
                password=edtPassword.getText().toString();
                presenter.verifyData(user,password);
                Log.d("teste,",user+"/"+password);
            }
        });
    }

    @Override
    public void setUserError(String msg){
        edtUser.requestFocus();
        edtUser.setError(msg);
    }

    @Override
    public void setPassError(String msg){
        edtPassword.requestFocus();
        edtPassword.setError(msg);
    }

    @Override
    public void findViews() {
        edtUser=findViewById(R.id.edtUser);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        edtUser.setText(prefs.getString(getString(R.string.user)));
        edtPassword.setText(prefs.getString(getString(R.string.password)));
    }
}
