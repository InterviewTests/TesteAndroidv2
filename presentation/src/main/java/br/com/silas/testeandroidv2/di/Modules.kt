package br.com.silas.testeandroidv2.di

import android.content.Context
import br.com.silas.data.local.PreferencesRepositoryImpl
import br.com.silas.data.remote.api.TesteAndroidV2Service
import br.com.silas.data.remote.api.TesteAndroidv2ServiceImpl
import br.com.silas.data.remote.login.LoginRepositoryImpl
import br.com.silas.data.remote.login.UserMapper
import br.com.silas.domain.Schedulers
import br.com.silas.domain.preferences.PreferencesRepository
import br.com.silas.domain.user.LoginInteractor
import br.com.silas.domain.user.LoginRepository
import br.com.silas.testeandroidv2.scheduler.AppScheduler
import br.com.silas.testeandroidv2.ui.user.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentaionModule = module {
    viewModel { LoginViewModel(get()) }
}
val domainModule = module {
    single<Schedulers> { AppScheduler() }
    single {
        LoginInteractor(get(), get())
    }
}
val dataModule = module {
    factory<TesteAndroidV2Service> { TesteAndroidv2ServiceImpl() }
    single { UserMapper() }
    single { androidContext().getSharedPreferences("teste-android-v2", Context.MODE_PRIVATE) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get(), get(), get()) }
}