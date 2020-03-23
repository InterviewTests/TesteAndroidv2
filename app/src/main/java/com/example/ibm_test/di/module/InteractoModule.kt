package com.example.ibm_test.di.module

import com.example.ibm_test.clean_code.login.interactor.LoginInteractorInput
import com.example.ibm_test.clean_code.login.interactor.LoginInteractorOutput
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import com.example.ibm_test.localstorage.UserStorage
import com.example.ibm_test.service.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractoModule{
    @Provides
    @Singleton
    fun provideLoginPresenterInput(loginPresenterInput: LoginPresenterInput, userService: UserService, userStorage: UserStorage): LoginInteractorInput
            = LoginInteractorOutput(loginPresenterInput, userService, userStorage)
}