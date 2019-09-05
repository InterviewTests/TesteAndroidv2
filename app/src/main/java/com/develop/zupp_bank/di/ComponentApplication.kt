package com.develop.zupp_bank.di

import com.develop.zupp_bank.infrastructure.repositories.disk.SPUtils
import com.develop.zupp_bank.presentation.login.LoginPresenter
import com.develop.zupp_bank.presentation.main.ZupApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ModuleRepository::class, ModuleContext::class, ModuleApi::class, ModuleHttp::class])
interface ComponentApplication {

    fun inject(zupApplication: ZupApplication)
    fun inject(loginPresenter: LoginPresenter)
    fun inject(spUtils: SPUtils)

}