package com.renanferrari.testeandroidv2.application;

import android.app.Application;
import android.os.StrictMode;
import com.renanferrari.testeandroidv2.application.di.components.AppComponent;

public class App extends Application {

  private static AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();

    StrictMode.enableDefaults();

    appComponent = AppComponent.Initializer.init(this);
  }

  public static synchronized AppComponent getAppComponent() {
    if (appComponent == null) {
      throw new IllegalStateException("AppComponent instance is null");
    }

    return appComponent;
  }
}