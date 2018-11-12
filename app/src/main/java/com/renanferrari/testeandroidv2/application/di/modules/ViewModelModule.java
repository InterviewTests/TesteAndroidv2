package com.renanferrari.testeandroidv2.application.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.renanferrari.testeandroidv2.application.common.utils.ViewModelFactory;
import com.renanferrari.testeandroidv2.application.di.keys.ViewModelKey;
import com.renanferrari.testeandroidv2.application.ui.login.LoginViewModel;
import com.renanferrari.testeandroidv2.application.ui.statements.StatementsViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import javax.inject.Singleton;

@Module public abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(LoginViewModel.class)
  abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(StatementsViewModel.class)
  abstract ViewModel bindStatementsViewModel(StatementsViewModel statementsViewModel);

  @Binds
  @Singleton
  abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}