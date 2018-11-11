package com.renanferrari.testeandroidv2.application.common.providers;

import com.renanferrari.testeandroidv2.common.RxUtils;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider {
  private final Scheduler subscribeOnScheduler;
  private final Scheduler observeOnScheduler;

  private SchedulerProvider(final Scheduler subscribeOnScheduler,
      final Scheduler observeOnScheduler) {
    this.subscribeOnScheduler = subscribeOnScheduler;
    this.observeOnScheduler = observeOnScheduler;
  }

  public static SchedulerProvider createDefault() {
    return new SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread());
  }

  public static SchedulerProvider forTesting() {
    return new SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline());
  }

  public Scheduler getSubscribeOnScheduler() {
    return subscribeOnScheduler;
  }

  public Scheduler getObserveOnScheduler() {
    return observeOnScheduler;
  }

  public <T> RxUtils.SchedulersTransformer<T> applySchedulers() {
    return RxUtils.applySchedulers(subscribeOnScheduler, observeOnScheduler);
  }
}