package com.renanferrari.testeandroidv2.application.di.modules;

import android.content.Context;
import com.renanferrari.testeandroidv2.application.App;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = {
    ActivityModule.class,
    ViewModelModule.class
}) public class AppModule {

  @Provides @Singleton Context provideApplicationContext(final App app) {
    return app.getApplicationContext();
  }

  @Provides @Singleton SchedulerProvider provideSchedulerProvider() {
    return SchedulerProvider.createDefault();
  }
}