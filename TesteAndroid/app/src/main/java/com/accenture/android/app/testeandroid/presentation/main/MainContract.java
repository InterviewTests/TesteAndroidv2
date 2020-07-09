package com.accenture.android.app.testeandroid.presentation.main;

import com.accenture.android.app.testeandroid.domain.Statement;
import com.accenture.android.app.testeandroid.presentation.BasePresenter;
import com.accenture.android.app.testeandroid.presentation.BaseView;

import java.util.ArrayList;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
interface MainContract {
    interface Presenter extends BasePresenter {
        void buscarStatements(Long userId);
    }

    interface View extends BaseView<Presenter> {
        void setLoading();

        void unsetLoading();

        void setContent();

        void unsetContent();

        void setFeedback(String message);

        void setStatements(ArrayList<Statement> statements);
    }
}