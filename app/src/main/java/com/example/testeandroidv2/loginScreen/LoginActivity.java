package com.example.testeandroidv2.loginScreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.testeandroidv2.R;
import com.example.testeandroidv2.databinding.LoginScreenBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

interface LoginActivityInput {
    void displayLoginData(UserViewModel userViewModel);
    void displayLoginErro(Object erro);
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    LoginInteractorInput output;
    List<UserViewModel> listOfLoginViewModel = new ArrayList<>();
    LoginRouter router;
    Object error;
    LoginModel login = new LoginModel("", "");

    private LoginScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login_screen);
        binding.setActivity(this);

        binding.setLogin(login);
        setIsErroVisible(false);

        LoginConfigurator.INSTANCE.configure(this);
        UserViewModel userViewModel = checkSharedPreferences();
        if(userViewModel != null)
            displayLoginData(userViewModel);
    }

    private void setIsErroVisible(Boolean isVisible){
        binding.setIsErroVisible(isVisible);
    }

    public void login(){
        setIsErroVisible(false);
        binding.setBtnText(getString(R.string.loginScreenBtnWaitMessage));
        fetchMetaData();
    }

    public void fetchMetaData(){
        LoginRequest aLoginRequest = new LoginRequest();
        aLoginRequest.user = login.login;
        aLoginRequest.password = login.password;
        output.fetchLoginData(aLoginRequest);
    }

    @Override
    public void displayLoginData(UserViewModel userViewModel) {
        listOfLoginViewModel.add(userViewModel);
        router.onItemClick(null, null, 0, 0);
    }

    @Override
    public void displayLoginErro(Object erro){
        this.error = erro;
        binding.setErroMessage(this.error.toString());
        binding.setBtnText(null);
        setIsErroVisible(true);
    }

    private UserViewModel checkSharedPreferences(){
        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        String jsonUserModel = preferences.getString("user_login", null);
        if(jsonUserModel != null)
            return new Gson().fromJson(jsonUserModel, UserViewModel.class);
        return null;
    }
}
