package br.com.rms.bankapp.di.module

import br.com.rms.bankapp.data.repository.user.UserRepository
import br.com.rms.bankapp.ui.home.HomeContract
import br.com.rms.bankapp.ui.home.HomePresenter
import br.com.rms.bankapp.ui.login.LoginContract
import br.com.rms.bankapp.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideLoginPresenter(userRepository: UserRepository): LoginContract.Presenter = LoginPresenter(userRepository)

    @Provides
    fun provideHomePresenter(): HomeContract.Presenter = HomePresenter()
}