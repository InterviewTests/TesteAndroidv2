package com.gft.testegft.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gft.testegft.statements.StatementsViewModel;
import com.gft.testegft.util.ViewModelFactory;
import com.gft.testegft.di.util.ViewModelKey;
import com.gft.testegft.login.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindListViewModel(LoginViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(StatementsViewModel.class)
    abstract ViewModel bindStatementViewModel(StatementsViewModel detailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
