package com.renanferrari.testeandroidv2.application.di.modules;

import com.renanferrari.testeandroidv2.application.di.scopes.ActivityScope;
import com.renanferrari.testeandroidv2.application.ui.login.LoginActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module public abstract class ActivityModule {
  @ActivityScope @ContributesAndroidInjector abstract LoginActivity contributeLoginActivity();
}