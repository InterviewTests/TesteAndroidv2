package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class HomeFragmentModule {

    @FragmentScope
    @Binds
    internal abstract fun provideView(homeFragment: HomeFragment): HomeContract.View

    @FragmentScope
    @Binds
    internal abstract fun providePresenter(homePresenter: HomePresenter): HomeContract.Presenter

}
