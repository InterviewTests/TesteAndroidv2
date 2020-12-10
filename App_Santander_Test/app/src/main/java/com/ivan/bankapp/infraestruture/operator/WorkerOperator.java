package com.ivan.bankapp.infraestruture.operator;

import io.reactivex.schedulers.Schedulers;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class WorkerOperator<T> implements rx.Observable.Transformer<T, T> {


    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return null;
    }
}
