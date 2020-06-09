package com.bank.testeandroidv2.loginScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bank.testeandroidv2.BankSharedPreferences;
import com.bank.testeandroidv2.R;
import com.bank.testeandroidv2.util.DialogUtil;


interface LoginActivityInput {
    void processLoginDataForm(LoginViewModel viewModel);

    void callNextScene(LoginViewModel viewModel);

    void callApiError(LoginViewModel viewModel);
}


public class LoginActivity extends AppCompatActivity
        implements LoginActivityInput {

    public static String TAG = LoginActivity.class.getSimpleName();
    LoginInteractorInput output;
    LoginRouter router;

    private EditText userEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private DialogUtil dialogUtil;

    private BankSharedPreferences bankSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        LoginConfigurator.INSTANCE.configure(this);
        dialogUtil = new DialogUtil(this);
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
        dialogUtil.showProgress(getResources().getString(R.string.login_loading_input_validation));
        LoginRequest aLoginRequest = new LoginRequest();
        aLoginRequest.user = userEditText.getText().toString();
        aLoginRequest.password = passwordEditText.getText().toString();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                output.validateLoginData(aLoginRequest);
            }
        }, 1000);
    }

    public void submitForm() {
        dialogUtil.showProgress(getResources().getString(R.string.login_loading_submit));
        LoginRequest aLoginRequest = new LoginRequest();
        aLoginRequest.user = userEditText.getText().toString();
        aLoginRequest.password = passwordEditText.getText().toString();
        bankSharedPreferences.saveUserToSharedpreference(aLoginRequest.user);
        bankSharedPreferences.savePasswordToSharedpreference(aLoginRequest.password);
        output.requestLoginDataOnServer(aLoginRequest);
    }


    @Override
    public void processLoginDataForm(LoginViewModel viewModel) {
        dialogUtil.hideProgress();
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
    public void callNextScene(LoginViewModel viewModel) {
        dialogUtil.hideProgress();
        router.passDataToNextScene(viewModel.userAccount);
    }

    @Override
    public void callApiError(LoginViewModel viewModel) {
        dialogUtil.hideProgress();
        Context context = getApplicationContext();
        dialogUtil.showErrorMessage(viewModel.error.toString());
    }
}

