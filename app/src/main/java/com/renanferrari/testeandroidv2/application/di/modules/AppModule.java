package com.renanferrari.testeandroidv2.application.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.renanferrari.testeandroidv2.application.App;
import com.renanferrari.testeandroidv2.application.common.providers.AndroidResourceProvider;
import com.renanferrari.testeandroidv2.application.common.providers.ResourceProvider;
import com.renanferrari.testeandroidv2.application.common.providers.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import java.util.Locale;
import javax.inject.Singleton;

@Module(includes = {
    ActivityModule.class,
    ViewModelModule.class
}) public class AppModule {

  @Provides @Singleton Context provideApplicationContext(final App app) {
    return app.getApplicationContext();
  }

  @Provides @Singleton SharedPreferences provideSharedPreferences(final Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  @Provides @Singleton ResourceProvider provideResourceProvider(final Context context) {
    return new AndroidResourceProvider(context);
  }

  @Provides @Singleton SchedulerProvider provideSchedulerProvider() {
    return SchedulerProvider.createDefault();
  }

  @Provides @Singleton Locale provideLocale() {
    return new Locale("pt", "BR");
  }
}