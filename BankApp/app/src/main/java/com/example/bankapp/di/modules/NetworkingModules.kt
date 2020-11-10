package com.example.bankapp.di.modules

import com.example.data.api.BankApi
import com.example.data.api.base.ApiClient
import com.example.data.repositories.BankRepository
import com.example.domain.repositorios.IBankRepositorio
import org.koin.dsl.bind
import org.koin.dsl.module

internal val NetworkingModules = module {
    single { ApiClient.createService(BankApi::class.java) }
    factory { BankRepository(get()) } bind IBankRepositorio::class
}
