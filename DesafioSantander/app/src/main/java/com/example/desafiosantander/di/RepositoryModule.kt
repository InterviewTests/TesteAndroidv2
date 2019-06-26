package com.example.desafiosantander.di

import com.example.desafiosantander.data.repository.hawk.HawkContract
import com.example.desafiosantander.data.repository.hawk.HawkRepository
import com.example.desafiosantander.data.repository.login.LoginContract
import com.example.desafiosantander.data.repository.login.LoginRepository
import com.example.desafiosantander.data.repository.summary.SummaryContract
import com.example.desafiosantander.data.repository.summary.SummaryRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<LoginContract> { LoginRepository(get()) }
    single<SummaryContract> { SummaryRepository(get()) }
    single<HawkContract> { HawkRepository() }

}