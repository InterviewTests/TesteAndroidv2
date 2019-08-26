package com.valber.santandertestvalber

import android.app.Application
import com.valber.data.network.createNetworkClient
import com.valber.data.platform.NetworkHandler
import com.valber.data.remote.BankApi
import com.valber.data.remote.ServiceBank
import com.valber.data.remote.ServiceBankImpl
import com.valber.data.repository.BankRepository
import com.valber.data.repository.BankRepositoryImpl
import com.valber.domain2.LoginCase
import com.valber.domain2.StatementCase
import com.valber.santandertestvalber.presentation.LoginViewModel
import com.valber.santandertestvalber.presentation.StatementViewModel
import com.valber.santandertestvalber.ui.LoginActivity
import com.valber.santandertestvalber.ui.StatementActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopeModule))
    }
}

private val appModule = module {
    single<ServiceBank> { ServiceBankImpl(backApi) }
}

private val dataModule = module {
    single { NetworkHandler(androidContext()) }
    factory<BankRepository> { BankRepositoryImpl(get()) }
}

private val scopeModule = module {

    scope(named<LoginActivity>()) {
        viewModel { LoginViewModel(get()) }
        scoped { LoginCase(get(), get()) }
    }

    scope(named<StatementActivity>()) {
        viewModel { StatementViewModel(get()) }
        scoped { StatementCase(get(), get()) }
    }
}
private const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)
private val backApi: BankApi = retrofit.create(BankApi::class.java)
