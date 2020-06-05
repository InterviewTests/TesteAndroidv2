package com.bank.testeandroidv2.loginScreen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.bank.testeandroidv2.R;
import com.bank.testeandroidv2.BankSharedPreferences;


interface LoginActivityInput {
    void processLoginDataForm(LoginViewModel viewModel);
    void callNextScene(UserAccount response);
    void callApiError(String error);
}


public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    private EditText userEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private BankSharedPreferences bankSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        LoginConfigurator.INSTANCE.configure(this);
        bankSharedPreferences = new BankSharedPreferences(this);

        bindViews();

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    private void bindViews() {
        userEditText = findViewById(R.id.et_user);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);

        userEditText.setText(bankSharedPreferences.getUserFromSharedPreference());
        userEditText.setText(bankSharedPreferences.getUserFromSharedPreference());
    }

    public void validateForm() {
        LoginRequest aLoginRequest = new LoginRequest();
        aLoginRequest.user = userEditText.getText().toString();
        aLoginRequest.password = passwordEditText.getText().toString();
        output.validateLoginData(aLoginRequest);
    }

    public void submitForm() {
        LoginRequest aLoginRequest = new LoginRequest();
        aLoginRequest.user = userEditText.getText().toString();
        aLoginRequest.password = passwordEditText.getText().toString();
        bankSharedPreferences.saveUserToSharedpreference(aLoginRequest.user);
        bankSharedPreferences.savePasswordToSharedpreference(aLoginRequest.password);
        output.requestLoginDataOnServer(aLoginRequest);
    }

    @Override
    public void processLoginDataForm(LoginViewModel viewModel) {
        Log.e(TAG, "processLoginDataForm() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
        String fieldsOkMsg = getResources().getString(R.string.validation_fields_ok);
        String statusMsg = getResources().getString(viewModel.status);
        if (fieldsOkMsg.equals(statusMsg)) {
            submitForm();
        } else {
            Context context = getApplicationContext();
            CharSequence text = statusMsg;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    public void callNextScene(UserAccount response) {
        router.passDataToNextScene(response);
    }

    @Override
    public void callApiError(String error) {
        Context context = getApplicationContext();
        CharSequence text = error;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

