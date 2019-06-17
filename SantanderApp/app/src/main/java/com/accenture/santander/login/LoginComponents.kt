package com.accenture.santander.login

import dagger.Component
import javax.inject.Singleton

@Component(modules = [LoginModulo::class])
interface LoginComponents {
    fun inject(fragment: LoginFragment)
    fun inject(presenter: LoginPresenter)
    fun inject(interactor: LoginInteractor)
}