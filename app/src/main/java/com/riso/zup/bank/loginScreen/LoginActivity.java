package com.riso.zup.bank.loginScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.riso.zup.bank.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_login) Button btnLogin;

    @BindView(R.id.edt_user) TextInputEditText edtUser;
    @BindView(R.id.edt_password) TextInputEditText edtPassword;

    @BindString(R.string.loading_message) String loadingMessage;
    @BindString(R.string.error_message) String erroMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initControl();
    }

    private void initControl(){
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            //To to when click in Login button
        }
    }

}
