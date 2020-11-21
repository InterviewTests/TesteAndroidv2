package com.example.bankapp.di.modules

import com.example.data.networking.BankApi
import com.example.data.networking.base.ApiClient
import com.example.data.repositories.BankRepository
import com.example.domain.repositories.IBankRepository
import org.koin.dsl.bind
import org.koin.dsl.module

internal val NetworkingModules = module {
    single { ApiClient.createService(BankApi::class.java) }
    factory { BankRepository(get()) } bind IBankRepository::class
}
