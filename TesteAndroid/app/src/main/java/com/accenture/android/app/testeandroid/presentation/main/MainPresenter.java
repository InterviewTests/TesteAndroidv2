package com.accenture.android.app.testeandroid.presentation.main;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.accenture.android.app.testeandroid.data.http.providers.StatementProvider;
import com.accenture.android.app.testeandroid.data.http.responses.generics.ErrorResponse;
import com.accenture.android.app.testeandroid.domain.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
class MainPresenter implements MainContract.Presenter {
    private final static String TAG = "CustomLog - " + MainPresenter.class.getSimpleName();

    private MainContract.View view;
    private Context context;

    private StatementProvider statementProvider;
    private StatementProvider.ExpectedResponseStatements statementsCallback;

    MainPresenter(Context context, Lifecycle lifecycle, MainContract.View view) {
        this.context = context;
        this.view = view;
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        this.initEvents();

        this.initProviders();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        this.statementProvider.finish();
    }

    private void initEvents() {
        this.statementsCallback = new StatementProvider.ExpectedResponseStatements() {
            @Override
            public void onSuccess(String message, List<Statement> statements) {
                view.unsetLoading();
                view.setContent();

                view.setStatements(new ArrayList<>(statements));
            }

            @Override
            public void onFailure(ErrorResponse error) {
                view.unsetLoading();
                view.setContent();

                view.setFeedback(String.format(Locale.getDefault(), "%d: %s", error.getStatusCode(), error.getMessage()));
            }
        };
    }

    private void initProviders() {
        String url = "https://bank-app-test.herokuapp.com/api/";

        this.statementProvider = new StatementProvider(url);
    }

    @Override
    public void buscarStatements(Long userId) {
        this.view.setLoading();
        this.view.unsetContent();

        this.statementProvider.buscarStatements(this.statementsCallback, userId);
    }
}