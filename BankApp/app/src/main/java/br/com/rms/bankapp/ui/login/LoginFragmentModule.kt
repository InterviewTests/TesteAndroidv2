package br.com.rms.bankapp.ui.login

import br.com.rms.bankapp.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class LoginFragmentModule {

    @FragmentScope
    @Binds
    internal abstract fun provideView(loginFragment: LoginFragment): LoginContract.View


    @FragmentScope
    @Binds
    internal abstract fun providePresenter(loginPresenter: LoginPresenter): LoginContract.Presenter

}
