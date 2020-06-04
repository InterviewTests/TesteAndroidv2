package com.gft.testegft.login;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.login.enums.EnumPasswordErrors;
import com.gft.testegft.login.enums.EnumUserErrors;
import com.gft.testegft.login.utils.LoginValidation;
import com.gft.testegft.network.ApiRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends BaseViewModel {

    private final ApiRepository repoRepository;
    private CompositeDisposable disposable;

    @Inject
    public LoginViewModel(ApiRepository apiRepository) {
        repoRepository = apiRepository;
        disposable = new CompositeDisposable();
    }

    private MutableLiveData<String> user = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<String> userError = new MutableLiveData<>();
    private MutableLiveData<String> passwordError = new MutableLiveData<>();

    void validateUser() {
        if (user.getValue() == null || user.getValue().isEmpty())
            userError.setValue(EnumUserErrors.NULL.desc);
        else if (!LoginValidation.userValidation(user.getValue()))
            userError.setValue(EnumUserErrors.INVALID.desc);
    }

    void validatePassword() {
        if (password.getValue() == null || password.getValue().isEmpty())
            passwordError.setValue(EnumPasswordErrors.NULL.desc);
        else if (!LoginValidation.passwordValidation(password.getValue()))
            passwordError.setValue(EnumPasswordErrors.INVALID.desc);
    }

    public void login() {
        repoRepository.login(user.getValue(), password.getValue());
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
