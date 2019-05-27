package com.example.testeacclogin.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

import com.example.testeacclogin.data.LoginRepository;
import com.example.testeacclogin.data.Result;
import com.example.testeacclogin.data.model.LoggedInUser;
import com.example.testeacclogin.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        //Valida se é email ou CPF
        if (username == "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$ | d{3}.?d{3}.?d{3}-?d{2}") {

            return Patterns.EMAIL_ADDRESS.matcher(username).matches();


        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {

        if (password == null) return false;

        boolean Numero = false;
        boolean Maiuscula = false;
        boolean Minuscula = false;
        boolean Simbolo = false;
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                Numero = true;
            } else if (c >= 'A' && c <= 'Z') {
                Maiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                Minuscula = true;
            } else {
                Simbolo = true;
            }
        }

        return Numero && Maiuscula && Minuscula && Simbolo;
    }
}
