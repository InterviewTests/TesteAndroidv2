package com.accenture.android.app.testeandroid.presentation.login;

import com.accenture.android.app.testeandroid.presentation.BasePresenter;
import com.accenture.android.app.testeandroid.presentation.BaseView;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
interface LoginContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
