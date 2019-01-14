package br.com.accenture.santander.wallacebaldenebre.ui.login;

import br.com.accenture.santander.wallacebaldenebre.ui.base.MvpPresenter;
import br.com.accenture.santander.wallacebaldenebre.ui.base.MvpView;

public interface LoginContract {
    interface View extends MvpView {
        void makeLogin();

        void setUp();
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void makeLogin(LoginActivity login, String user, String password);
    }
}
