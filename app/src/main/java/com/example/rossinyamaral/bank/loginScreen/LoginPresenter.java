package com.example.rossinyamaral.bank.loginScreen;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

interface LoginPresenterInput {
    public void presentLoginData(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public static String TAG = LoginPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<LoginActivityInput> output;


    @Override
    public void presentLoginData(LoginResponse response) {
        // Log.e(TAG, "presentLoginData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.name = response.userAccountModel.getName();
        loginViewModel.bankAccount = response.userAccountModel.getBankAccount();
        loginViewModel.agency = response.userAccountModel.getAgency();
        loginViewModel.balance = response.userAccountModel.getBalance();
        output.get().displayLoginData(response.userAccountModel);

    }


}
