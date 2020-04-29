package com.br.example.fakebank.presentations.viewModels;

import androidx.lifecycle.ViewModel;

import com.br.example.fakebank.domains.services.CurrencyService;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.Erros.InvalidApiAccessError;
import com.br.example.fakebank.presentations.handles.CurrencyHandle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CurrencyViewModel extends ViewModel implements CurrencyViewModelInterface{

    private CurrencyService currencyService;
    private CurrencyHandle currencyHandle;

    private CompositeDisposable disposable = new CompositeDisposable();

    public CurrencyViewModel(CurrencyService currencyService, CurrencyHandle currencyHandle) {
        this.currencyService = currencyService;
        this.currencyHandle = currencyHandle;
    }

    @Override
    public void listStatements(Integer userId) {
        currencyHandle.setLoading(true);
        disposable.add(currencyService.listStatements(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        greeting -> {
                            if (greeting.getStatementList() != null){
                                currencyHandle.reloadListStatement(greeting.getStatementList());
                            }else{
                                currencyHandle.showError(new ErrorUtils(greeting.getError().getMessage()));
                            }
                            currencyHandle.setLoading(false);
                        },
                        throwable -> {
                            currencyHandle.showError(new InvalidApiAccessError());
                            currencyHandle.setLoading(false);
                        }
                )
        );
    }

    @Override
    public void didClickLogout(){
        currencyHandle.actionLogout();
    }
}
