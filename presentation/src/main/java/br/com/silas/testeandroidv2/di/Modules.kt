package br.com.silas.testeandroidv2.di

import br.com.silas.domain.Schedulers
import br.com.silas.domain.user.LoginInteractor
import br.com.silas.testeandroidv2.scheduler.AppScheduler
import br.com.silas.testeandroidv2.ui.user.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentaionModule = module {
    viewModel { LoginViewModel(get()) }
}
val domainModule = module {
    single<Schedulers> { AppScheduler() }
    single {
        LoginInteractor(loginRepository, get())
    }
}
val dataModule = module {  }