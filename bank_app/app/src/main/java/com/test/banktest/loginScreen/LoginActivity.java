package com.test.banktest.loginScreen;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.test.banktest.R;
import com.test.banktest.util.TextUtils;


interface LoginActivityInput {
    void displayLastUser(LoginViewModel viewModel);
    void displayLoginData(LoginViewModel viewModel);
}

public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    TextInputLayout inputUser;
    TextInputEditText edtUser;
    TextInputLayout inputPassword;
    TextInputEditText edtPassword;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();

        LoginConfigurator.INSTANCE.configure(this);
        LoginRequest aLoginRequest = new LoginRequest();
        aLoginRequest.context = this;
        output.getLastUser(aLoginRequest);
    }

    private void bindViews() {
        this.inputUser = this.findViewById(R.id.inputUser);
        this.edtUser = this.findViewById(R.id.edtUser);
        this.inputPassword = this.findViewById(R.id.inputPassword);
        this.edtPassword = this.findViewById(R.id.edtPassword);
        this.btLogin = this.findViewById(R.id.btLogin);

        this.btLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                LoginRequest request = new LoginRequest();
                request.context = LoginActivity.this;
                request.user = LoginActivity.this.edtUser.getText().toString();
                request.password = LoginActivity.this.edtPassword.getText().toString();

                setFormEnabled(false);

                output.login(request);
            }
        });
    }

    public void setFormEnabled(boolean enabled){
        edtPassword.setEnabled(enabled);
        edtUser.setEnabled(enabled);
        btLogin.setEnabled(enabled);
        btLogin.setText(enabled ? R.string.login : R.string.pleaseWait);
    }

    @Override
    public void displayLastUser(LoginViewModel viewModel) {
        edtUser.setText(viewModel.lastUser);
    }

    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        Log.e(TAG, "displayLoginData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data

        if(viewModel.userAccount != null){
            edtPassword.setText(null);
            setFormEnabled(true);
            Intent intent = router.navigateToSomeWhere(0);
            router.passUserToNextScene(viewModel.userAccount,intent);
            this.startActivity(intent);
            this.finish();
        } else {
            inputUser.setError(viewModel.userError);
            inputUser.setErrorEnabled(!TextUtils.isEmpty(viewModel.userError));

            inputPassword.setError(viewModel.passwordError);
            inputPassword.setErrorEnabled(!TextUtils.isEmpty(viewModel.passwordError));

            setFormEnabled(true);
        }
    }
}
