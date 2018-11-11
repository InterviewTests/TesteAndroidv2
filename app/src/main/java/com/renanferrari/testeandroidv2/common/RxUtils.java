package com.renanferrari.testeandroidv2.common;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

public class RxUtils {

  public static <T> RxUtils.SchedulersTransformer<T> applySchedulers() {
    return applySchedulers(Schedulers.io(), AndroidSchedulers.mainThread());
  }

  public static <T> RxUtils.SchedulersTransformer<T> applySchedulers(Scheduler subscribeOnScheduler,
      Scheduler observeOnScheduler) {
    return new RxUtils.SchedulersTransformer<>(subscribeOnScheduler, observeOnScheduler);
  }

  public static class SchedulersTransformer<T> implements ObservableTransformer<T, T>,
      FlowableTransformer<T, T>,
      SingleTransformer<T, T>,
      MaybeTransformer<T, T>,
      CompletableTransformer {

    private final Scheduler subscribeOnScheduler;
    private final Scheduler observeOnScheduler;

    private SchedulersTransformer(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
      this.subscribeOnScheduler = subscribeOnScheduler;
      this.observeOnScheduler = observeOnScheduler;
    }

    @Override public ObservableSource<T> apply(Observable<T> upstream) {
      return upstream.subscribeOn(this.subscribeOnScheduler).observeOn(this.observeOnScheduler);
    }

    @Override public Publisher<T> apply(Flowable<T> upstream) {
      return upstream.subscribeOn(this.subscribeOnScheduler).observeOn(this.observeOnScheduler);
    }

    @Override public SingleSource<T> apply(Single<T> upstream) {
      return upstream.subscribeOn(this.subscribeOnScheduler).observeOn(this.observeOnScheduler);
    }

    @Override public MaybeSource<T> apply(Maybe<T> upstream) {
      return upstream.subscribeOn(this.subscribeOnScheduler).observeOn(this.observeOnScheduler);
    }

    @Override public CompletableSource apply(Completable upstream) {
      return upstream.subscribeOn(this.subscribeOnScheduler).observeOn(this.observeOnScheduler);
    }
  }
}