package com.br.example.fakebank.presentations.viewModels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.br.example.fakebank.domains.services.LoginService;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.Erros.InvalidApiAccessError;
import com.br.example.fakebank.presentations.Erros.InvalidPasswordError;
import com.br.example.fakebank.presentations.Erros.InvalidUserError;
import com.br.example.fakebank.presentations.handles.LoginHandle;
import com.br.example.fakebank.presentations.utils.RegexValidateUtil;
import com.br.example.fakebank.presentations.utils.StatusPreferenceUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel implements LoginViewModelInterface {

    private LoginService mainService;
    private LoginHandle loginHandle;

    public ObservableField<String> user = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    public LoginViewModel(LoginService mainService, LoginHandle loginHandle)
    {
        this.mainService = mainService;
        this.loginHandle = loginHandle;
    }

    public void didClickAuthLogin()
    {
        if (user.get() == null || !isUserValid(user.get())){
            loginHandle.showError(new InvalidUserError());
        }else if(password.get() == null || !isPasswordValid(password.get())) {
            loginHandle.showError(new InvalidPasswordError());
        }else{
            loginHandle.setLoading(false);
            authLogin(user.get(), password.get());
            loginHandle.setLoading(false);
        }
    }

    @Override
    public Boolean isUserValid(String userName)
    {
        RegexValidateUtil regexValidateUtil = new RegexValidateUtil();
        return regexValidateUtil.isValidField(RegexValidateUtil.EMAIL_PATTERN, userName) || regexValidateUtil.isValidField(RegexValidateUtil.CPF_PATTERN, userName);
    }

    @Override
    public Boolean isPasswordValid(String userPassword)
    {
        return new RegexValidateUtil().isValidField(RegexValidateUtil.PASSWORD_PATTERN, userPassword);
    }

    @Override
    public void authLogin(String userName, String userPassword) {
        disposable.add(mainService.authLogin(user.get(), password.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        greeting -> {
                            if (greeting.getUserAccount() != null){
                                loginHandle.sendCurrencyActivity(greeting.getUserAccount());
                                loginHandle.accessSharedPreference(StatusPreferenceUtil.WHITE,userName, userPassword);
                            }else{
                                loginHandle.showError(new ErrorUtils(greeting.getErrorEntity().getMessage()));
                            }
                        },
                        throwable -> loginHandle.showError(new InvalidApiAccessError())
                )
        );
    }
}
