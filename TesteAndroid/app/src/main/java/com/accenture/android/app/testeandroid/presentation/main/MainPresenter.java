package com.accenture.android.app.testeandroid.presentation.main;

import android.content.Context;

import androidx.lifecycle.Lifecycle;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
class MainPresenter implements MainContract.Presenter {
    private final static String TAG = "CustomApp - " + MainPresenter.class.getSimpleName();

    private MainContract.View view;
    private Context context;

    MainPresenter(Context context, Lifecycle lifecycle, MainContract.View view) {
        this.context = context;
        this.view = view;
        lifecycle.addObserver(this);
    }
}
