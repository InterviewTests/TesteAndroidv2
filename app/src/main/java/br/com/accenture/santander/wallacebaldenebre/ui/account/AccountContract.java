package br.com.accenture.santander.wallacebaldenebre.ui.account;

import br.com.accenture.santander.wallacebaldenebre.model.Account;
import br.com.accenture.santander.wallacebaldenebre.ui.base.MvpPresenter;
import br.com.accenture.santander.wallacebaldenebre.ui.base.MvpView;

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
