package com.develop.tcs_bank.di

import com.develop.tcs_bank.infrastructure.repositories.disk.SPUtils
import com.develop.tcs_bank.presentation.data.DataPresenter
import com.develop.tcs_bank.presentation.login.LoginPresenter
import com.develop.tcs_bank.presentation.main.TcsApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ModuleRepository::class, ModuleContext::class, ModuleApi::class, ModuleHttp::class])
interface ComponentApplication {

    fun inject(zupApplication: TcsApplication)
    fun inject(loginPresenter: LoginPresenter)
    fun inject(spUtils: SPUtils)
    fun inject(dataPresenter: DataPresenter)
}