package com.example.bankapp.di.modulos

import com.example.data.api.BankApi
import com.example.data.api.base.ClienteApi
import com.example.data.repositorios.BankRepositorio
import com.example.domain.repositorios.IBankRepositorio
import org.koin.dsl.bind
import org.koin.dsl.module

internal val ModulosData = module {
    single { ClienteApi.createService(BankApi::class.java) }
    factory { BankRepositorio(get()) } bind IBankRepositorio::class
}
