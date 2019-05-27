package com.caique.everis.testeandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.caique.everis.testeandroid.data.local.AppDataBase;
import com.caique.everis.testeandroid.model.AccountInfoResponse;
import com.caique.everis.testeandroid.model.Login;
import com.caique.everis.testeandroid.model.LoginResponse;
import com.caique.everis.testeandroid.repository.BankRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankViewModel extends AndroidViewModel{

    public BankRepository bankRepository;
    private LiveData<Login> loginLiveData;

    public BankViewModel(@NonNull Application application) {
        super(application);
        bankRepository = BankRepository.getInstance(AppDataBase.getAppDatabase(application));
        loginLiveData = bankRepository.getLoginDao();
    }

    public LiveData<LoginResponse> getLoginResponse(Login login) {
        return bankRepository.getLoginResponse(login);
    }

    public LiveData<AccountInfoResponse> getAccountInfoResponse() {
        return bankRepository.getAccountInfoResponse();
    }

    public LiveData<Login> getLoginDao() {
        return loginLiveData;
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}