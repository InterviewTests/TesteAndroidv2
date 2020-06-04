package br.com.altran.santander.davidmelo.ui.account;

import br.com.altran.santander.davidmelo.model.Account;
import br.com.altran.santander.davidmelo.ui.base.MvpPresenter;
import br.com.altran.santander.davidmelo.ui.base.MvpView;

import java.util.HashMap;

public interface AccountContract {
    interface View extends MvpView {
        void showData();

        void setUp();
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void showDataFromServer(AccountActivity account, final AccountCallback<HashMap<String, Account>> callback);
    }
}
