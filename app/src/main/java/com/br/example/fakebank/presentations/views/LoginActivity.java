package com.br.example.fakebank.presentations.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.R;
import com.br.example.fakebank.databinding.ActivityLoginBinding;
import com.br.example.fakebank.domains.services.LoginService;
import com.br.example.fakebank.infrastructure.app.PreferenceFakeBank;
import com.br.example.fakebank.infrastructure.retrofit.entities.UserAccountEntity;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.Erros.InvalidPasswordError;
import com.br.example.fakebank.presentations.Erros.InvalidUserError;
import com.br.example.fakebank.presentations.handles.LoginHandle;
import com.br.example.fakebank.presentations.utils.StatusPreferenceUtil;
import com.br.example.fakebank.presentations.viewModels.LoginViewModel;
import com.br.example.fakebank.presentations.viewModels.viewModelFactories.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity implements LoginHandle {

    private ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;
    private PreferenceFakeBank preferenceFakeBank;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        LoginViewModelFactory loginViewModelFactory = new LoginViewModelFactory(new LoginService(), this);

        loginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        preferenceFakeBank = new PreferenceFakeBank(this);
        accessSharedPreference(StatusPreferenceUtil.READY, null,null);
    }

    @Override
    public void setLoading(Boolean isLoading)
    {
        if (isLoading) {
            activityLoginBinding.includeLoading.getRoot().setVisibility(View.VISIBLE);
        } else {
            activityLoginBinding.includeLoading.getRoot().setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(ErrorUtils error)
    {
        if (error instanceof InvalidUserError) {
            activityLoginBinding.editTextUser.requestFocus();
        }

        if (error instanceof InvalidPasswordError) {
            activityLoginBinding.editTextPassword.requestFocus();
        }

        showMessage(error.returnMessageByContext(this));
    }

    @Override
    public void sendCurrencyActivity(UserAccountEntity userAccountEntity)
    {
        Intent intent = new Intent(getApplicationContext(), StatementActivity.class);
        intent.putExtra("userAccount", userAccountEntity);
        startActivity(intent);
        finish();
    }

    @Override
    public void accessSharedPreference(StatusPreferenceUtil statusPreferenceUtil, String userName, String userPassword)
    {
        switch (statusPreferenceUtil){
            case READY:
                String currentUserName = preferenceFakeBank.getUserName();
                String currentUserPassword = preferenceFakeBank.getUserPassword();
                if ((currentUserPassword != null) && (currentUserName != null)){
                    loginViewModel.user.set(currentUserName);
                    loginViewModel.password.set(currentUserPassword);
                }
                break;
            case WHITE:
                preferenceFakeBank.setNewPreference(userName, userPassword);
                break;
        }
    }

    private void showMessage(String message)
    {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
