package com.jisellemartins.bank.di

import com.jisellemartins.bank.repositories.BankRepository
import com.jisellemartins.bank.service.BankService
import com.jisellemartins.bank.service.service
import com.jisellemartins.bank.viewmodel.LoginViewModel
import com.jisellemartins.bank.viewmodel.StatementsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val moduleGlobal = module {
    single<Retrofit> { service.api }
    single<BankService> { get<Retrofit>().create(BankService::class.java)}
    single<BankRepository> { BankRepository(get()) }
    viewModel<LoginViewModel>{ LoginViewModel(get())}
    viewModel<StatementsViewModel>{ StatementsViewModel(get()) }
}