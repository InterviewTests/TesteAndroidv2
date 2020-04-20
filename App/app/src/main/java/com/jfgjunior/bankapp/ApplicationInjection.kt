package com.jfgjunior.bankapp

import android.content.Context.MODE_PRIVATE
import com.jfgjunior.bankapp.data.external.CredentialsManager
import com.jfgjunior.bankapp.data.external.BankRepository
import com.jfgjunior.bankapp.data.external.Repository
import com.jfgjunior.bankapp.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NAME = "bank_preferences"

val applicationModule = module {

    single<Repository> { BankRepository() }

    factory { CredentialsManager(androidContext().getSharedPreferences(NAME, MODE_PRIVATE)) }

    viewModel { LoginViewModel(get()) }
}