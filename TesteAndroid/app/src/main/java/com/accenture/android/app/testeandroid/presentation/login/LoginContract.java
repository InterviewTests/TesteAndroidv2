package com.accenture.android.app.testeandroid.presentation.login;

import com.accenture.android.app.testeandroid.presentation.BasePresenter;
import com.accenture.android.app.testeandroid.presentation.BaseView;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
interface LoginContract {
    interface Presenter extends BasePresenter {
        void efetuarLogin(String user, String password);
    }

    interface View extends BaseView<Presenter> {
        void setLoading();

        void unsetLoading();

        void setContent();

        void unsetContent();

        void setFeedback(String message);

        void setError(String message);

        void unsetError();

        void setLoginRecente(String name);

        void navigateToMainActivity();
    }
}
