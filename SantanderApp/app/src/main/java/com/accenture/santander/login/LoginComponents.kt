package com.accenture.santander.login

import dagger.Component

@Component(modules = [LoginModule::class])
interface LoginComponents {
    fun inject(fragment: LoginFragment)
    fun inject(presenter: LoginPresenter)
    fun inject(interactor: LoginInteractor)
}