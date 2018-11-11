package com.renanferrari.testeandroidv2.application.di.components;

import com.renanferrari.testeandroidv2.application.App;
import com.renanferrari.testeandroidv2.application.di.modules.AppModule;
import com.renanferrari.testeandroidv2.application.di.modules.DomainModule;
import com.renanferrari.testeandroidv2.application.di.modules.InfrastructureModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    AppModule.class,
    DomainModule.class,
    InfrastructureModule.class,
    AndroidSupportInjectionModule.class,
}) public interface AppComponent extends AndroidInjector<App> {
  @Component.Builder interface Builder {
    @BindsInstance Builder app(App app);

    AppComponent build();
  }
}