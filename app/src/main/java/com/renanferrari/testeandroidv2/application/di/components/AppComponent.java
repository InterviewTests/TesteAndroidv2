package com.renanferrari.testeandroidv2.application.di.components;

import com.renanferrari.testeandroidv2.application.App;
import com.renanferrari.testeandroidv2.application.di.modules.AppModule;
import com.renanferrari.testeandroidv2.application.di.modules.DomainModule;
import com.renanferrari.testeandroidv2.application.di.modules.InfrastructureModule;
import com.renanferrari.testeandroidv2.application.ui.login.LoginActivity;
import com.renanferrari.testeandroidv2.application.ui.statements.StatementsActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    AppModule.class, DomainModule.class, InfrastructureModule.class
}) public interface AppComponent {

  void inject(LoginActivity loginActivity);

  void inject(StatementsActivity statementsActivity);

  final class Initializer {
    private Initializer() {}

    public static AppComponent init(final App app) {
      return DaggerAppComponent.builder()
          .appModule(new AppModule(app))
          .domainModule(new DomainModule())
          .infrastructureModule(new InfrastructureModule())
          .build();
    }
  }
}