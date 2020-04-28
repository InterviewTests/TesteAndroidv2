package com.br.example.fakebank.presentations.viewModels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.br.example.fakebank.domains.services.MainService;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.Erros.InvalidApiAccessError;
import com.br.example.fakebank.presentations.Erros.InvalidPasswordError;
import com.br.example.fakebank.presentations.Erros.InvalidUserError;
import com.br.example.fakebank.presentations.handles.MainHandle;
import com.br.example.fakebank.presentations.utils.RegexValidateUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel implements MainViewModelInterface{

    private MainService mainService;
    private MainHandle mainHandle;

    public ObservableField<String> user = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    public MainViewModel(MainService mainService, MainHandle mainHandle)
    {
        this.mainService = mainService;
        this.mainHandle = mainHandle;
    }

    public void didClickAuthLogin()
    {
        if (user.get() == null || !isUserValid(user.get())){
            mainHandle.showError(new InvalidUserError());
        }else if(password.get() == null || !isPasswordValid(password.get())) {
            mainHandle.showError(new InvalidPasswordError());
        }else{
            mainHandle.setLoading(false);
            authLogin(user.get(), password.get());
            mainHandle.setLoading(false);
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
                                mainHandle.sendCurrencyActivity(greeting.getUserAccount());
                            }else{
                                mainHandle.showError(new ErrorUtils(greeting.getErrorEntity().getMessage()));
                            }
                        },
                        throwable -> mainHandle.showError(new InvalidApiAccessError())
                )
        );
    }
}
