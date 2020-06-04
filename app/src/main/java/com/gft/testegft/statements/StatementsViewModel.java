package com.gft.testegft.statements;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.gft.testegft.network.ApiRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class StatementsViewModel extends ViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    @Inject
    StatementsViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();
        Log.i("tetete", "ta funcinoadn");
    }
}
