package com.accenture.android.app.testeandroid.presentation.main;

import com.accenture.android.app.testeandroid.presentation.BasePresenter;
import com.accenture.android.app.testeandroid.presentation.BaseView;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
interface MainContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}