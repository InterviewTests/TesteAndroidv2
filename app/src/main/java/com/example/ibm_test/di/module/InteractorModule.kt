package com.example.ibm_test.di.module

import com.example.ibm_test.clean_code.home.interactor.HomeInteractorOutput
import com.example.ibm_test.clean_code.home.presenter.HomePresenterOutput
import com.example.ibm_test.clean_code.login.interactor.LoginInteractorInput
import com.example.ibm_test.clean_code.login.interactor.LoginInteractorOutput
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.service.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule{
    @Provides
    @Singleton
    fun provideLoginInteractorInput(loginPresenterInput: LoginPresenterInput, userService: UserService, userStorage: UserStorage): LoginInteractorInput
            = LoginInteractorOutput(loginPresenterInput, userService, userStorage)

    @Provides
    @Singleton
    fun provideHomeInteractoInput(homePresenterInput: HomePresenterOutput, userService: UserService): HomeInteractorOutput
            = HomeInteractorOutput(homePresenterInput, userService)
}