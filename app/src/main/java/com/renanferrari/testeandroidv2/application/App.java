package com.renanferrari.testeandroidv2.application;

import android.os.StrictMode;
import com.renanferrari.testeandroidv2.application.di.components.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

  @Override public void onCreate() {
    super.onCreate();

    StrictMode.enableDefaults();
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().app(this).build();
  }
}