package com.br.example.fakebank.presentations.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.br.example.fakebank.R;
import com.br.example.fakebank.databinding.ActivityMainBinding;
import com.br.example.fakebank.domains.services.MainService;
import com.br.example.fakebank.presentations.utils.StatusPreferenceUtil;
import com.br.example.fakebank.infrastructure.app.PreferenceFakeBank;
import com.br.example.fakebank.infrastructure.retrofit.entities.UserAccountEntity;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.Erros.InvalidPasswordError;
import com.br.example.fakebank.presentations.Erros.InvalidUserError;
import com.br.example.fakebank.presentations.handles.MainHandle;
import com.br.example.fakebank.presentations.viewModels.MainViewModel;
import com.br.example.fakebank.presentations.viewModels.viewModelFactories.MainViewModelFactory;

public class MainActivity extends AppCompatActivity implements MainHandle {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;
    private PreferenceFakeBank preferenceFakeBank;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModelFactory mainViewModelFactory = new MainViewModelFactory(new MainService(), this);

        mainViewModel = new ViewModelProvider(this, mainViewModelFactory).get(MainViewModel.class);
        activityMainBinding.setMainViewModel(mainViewModel);
        preferenceFakeBank = new PreferenceFakeBank(this);
        accessSharedPreference(StatusPreferenceUtil.READY, null,null);
    }

    @Override
    public void setLoading(Boolean isLoading)
    {
        if (isLoading) {
            activityMainBinding.includeLoading.getRoot().setVisibility(View.VISIBLE);
        } else {
            activityMainBinding.includeLoading.getRoot().setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(ErrorUtils error)
    {
        if (error instanceof InvalidUserError) {
            activityMainBinding.editTextUser.requestFocus();
        }

        if (error instanceof InvalidPasswordError) {
            activityMainBinding.editTextPassword.requestFocus();
        }

        showMessage(error.returnMessageByContext(this));
    }

    @Override
    public void sendCurrencyActivity(UserAccountEntity userAccountEntity)
    {
        Intent intent = new Intent(getApplicationContext(), CurrencyActivity.class);
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
                    mainViewModel.user.set(currentUserName);
                    mainViewModel.password.set(currentUserPassword);
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
