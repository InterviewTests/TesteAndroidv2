package com.gft.testegft.login;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;
import com.gft.testegft.login.data.LoginResponse;
import com.gft.testegft.login.enums.EnumPasswordErrors;
import com.gft.testegft.login.enums.EnumUserErrors;
import com.gft.testegft.login.utils.LoginValidation;
import com.gft.testegft.network.ApiRepository;
import com.gft.testegft.util.GsonManager;
import com.gft.testegft.util.SharedPreferenceManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gft.testegft.util.Constants.LOGIN_RESPONSE_FLAG;
import static com.gft.testegft.util.Constants.USER_FLAG;

public class LoginViewModel extends BaseViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    private MutableLiveData<String> user = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<String> userError = new MutableLiveData<>();
    private MutableLiveData<String> passwordError = new MutableLiveData<>();
    private MutableLiveData<String> requestError = new MutableLiveData<>();

    private MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

    @Inject
    LoginViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

        getLastLoggedUser();
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

    MutableLiveData<String> getRequestError() {
        return requestError;
    }

    MutableLiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
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
            disposable.add(apiRepository.login(user.getValue(), password.getValue())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                        @Override
                        public void onSuccess(LoginResponse loginResponse) {
                            onLoginSuccess(loginResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            onLoginError(e);
                        }
                    }));
        } else {
            validateUser();
            validatePassword();
        }
    }

    private void onLoginSuccess(LoginResponse loginResponse) {
        setLastLoggerUser(user.getValue());
        if (loginResponse.getError() == null || loginResponse.getError().getCode() == 0) {
            SharedPreferenceManager.setName(LOGIN_RESPONSE_FLAG, GsonManager.toJson(loginResponse));
            this.loginResponse.setValue(loginResponse);
        } else {
            requestError.setValue(loginResponse.getError().getMessage());
        }
    }

    private void onLoginError(Throwable e) {
        requestError.setValue("Houve um erro em nossos servidores");
    }

    private void getLastLoggedUser() {
        String lastLogged = SharedPreferenceManager.getName(USER_FLAG);
        if (!lastLogged.equals("")){
            user.setValue(lastLogged);
        }
    }

    private void setLastLoggerUser(String user) {
       SharedPreferenceManager.setName(USER_FLAG, user);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

}
