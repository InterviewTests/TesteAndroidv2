package com.br.example.fakebank.presentations.viewModels;

import androidx.lifecycle.ViewModel;

import com.br.example.fakebank.domains.services.StatementService;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.Erros.InvalidApiAccessError;
import com.br.example.fakebank.presentations.handles.StatementHandle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class StatementViewModel extends ViewModel {

    private StatementService currencyService;
    private StatementHandle statementHandle;

    private CompositeDisposable disposable = new CompositeDisposable();

    public StatementViewModel(StatementService currencyService, StatementHandle statementHandle) {
        this.currencyService = currencyService;
        this.statementHandle = statementHandle;
    }

    public void listStatements(Integer userId) {
        statementHandle.setLoading(true);
        disposable.add(currencyService.listStatements(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        greeting -> {
                            if (greeting.getStatementList() != null){
                                statementHandle.reloadListStatement(greeting.getStatementList());
                            }else{
                                statementHandle.showError(new ErrorUtils(greeting.getError().getMessage()));
                            }
                            statementHandle.setLoading(false);
                        },
                        throwable -> {
                            statementHandle.showError(new InvalidApiAccessError());
                            statementHandle.setLoading(false);
                        }
                )
        );
    }

    public void didClickLogout(){
        statementHandle.actionLogout();
    }
}
