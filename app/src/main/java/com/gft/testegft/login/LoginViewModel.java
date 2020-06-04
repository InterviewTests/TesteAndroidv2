package com.gft.testegft.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.login.data.LoginResponse;
import com.gft.testegft.login.enums.EnumPasswordErrors;
import com.gft.testegft.login.enums.EnumUserErrors;
import com.gft.testegft.login.utils.LoginValidation;
import com.gft.testegft.network.ApiRepository;
import com.google.gson.Gson;


import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LoginViewModel extends BaseViewModel {

    private final ApiRepository repoRepository;
    private CompositeDisposable disposable;

    private MutableLiveData<String> user = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<String> userError = new MutableLiveData<>();
    private MutableLiveData<String> passwordError = new MutableLiveData<>();

    @Inject
    public LoginViewModel(ApiRepository apiRepository) {
        repoRepository = apiRepository;
        disposable = new CompositeDisposable();
    }

    void validateUser() {
        if (isUserEmpty(user.getValue()))
            userError.setValue(EnumUserErrors.NULL.desc);
        else if (isUserInvalid(user.getValue()))
            userError.setValue(EnumUserErrors.INVALID.desc);
    }

    void validatePassword() {
        if (password.getValue() == null || password.getValue().isEmpty())
            passwordError.setValue(EnumPasswordErrors.NULL.desc);
        else if (!LoginValidation.passwordValidation(password.getValue()))
            passwordError.setValue(EnumPasswordErrors.INVALID.desc);
    }

    private boolean isUserValid(String user) {
        return !isUserEmpty(user) && !isUserInvalid(user);
    }

    private boolean isUserEmpty(String user) {
        return user == null || user.isEmpty();
    }

    private boolean isUserInvalid(String user) {
        return !LoginValidation.userValidation(user);
    }

    private boolean isPasswordValid(String password) {
        return !isPasswordEmpty(password) && !isPasswordInvalid(password);
    }

    private boolean isPasswordEmpty(String password) {
        return password == null || password.isEmpty();
    }

    private boolean isPasswordInvalid(String password) {
        return !LoginValidation.passwordValidation(password);
    }

    public void login() {
        if (isUserValid(user.getValue()) && isPasswordValid(password.getValue())) {
            disposable.add(repoRepository.login(user.getValue(), password.getValue()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                        @Override
                        public void onSuccess(LoginResponse value) {
                            Log.i("login", new Gson().toJson(value));
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("error", Objects.requireNonNull(e.getLocalizedMessage()));
                        }
                    }));
        } else {
            Log.i("login", "login");


            validateUser();
            validatePassword();
        }
    }

    public MutableLiveData<String> getUser() {
        return user;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    MutableLiveData<String> getPasswordError() {
        return passwordError;
    }

    MutableLiveData<String> getUserError() {
        return userError;
    }
}
