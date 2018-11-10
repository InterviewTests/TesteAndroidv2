package com.renanferrari.testeandroidv2.application.di.modules;

import com.renanferrari.testeandroidv2.domain.interactors.Login;
import com.renanferrari.testeandroidv2.domain.model.auth.AuthService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class DomainModule {

  @Provides @Singleton Login provideLogin(final AuthService authService) {
    return new Login(authService);
  }
}