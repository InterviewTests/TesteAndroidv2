package br.com.silas.testeandroidv2.di

import android.content.Context
import br.com.silas.data.local.PreferencesRepositoryImpl
import br.com.silas.data.remote.api.ServiceTesteAndroidV2
import br.com.silas.data.remote.api.TesteAndroidv2ServiceImpl
import br.com.silas.data.remote.login.LoginRepositoryImpl
import br.com.silas.data.remote.login.UserMapper
import br.com.silas.data.remote.statements.StatementMapper
import br.com.silas.data.remote.statements.StatementsRepositoryImpl
import br.com.silas.domain.Schedulers
import br.com.silas.domain.preferences.PreferencesRepository
import br.com.silas.domain.statements.StatementsInteractor
import br.com.silas.domain.statements.StatementsRepository
import br.com.silas.domain.user.GetUserInteractor
import br.com.silas.domain.user.LoginInteractor
import br.com.silas.domain.user.LoginRepository
import br.com.silas.domain.user.SaveUserInteractor
import br.com.silas.testeandroidv2.scheduler.AppScheduler
import br.com.silas.testeandroidv2.ui.statements.StatementsViewModel
import br.com.silas.testeandroidv2.ui.user.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentaionModule = module {
    viewModel { StatementsViewModel(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
}
val domainModule = module {
    single<Schedulers> { AppScheduler() }
    single { LoginInteractor(get(), get()) }
    single { StatementsInteractor(get(), get()) }
    single { GetUserInteractor(get()) }
    single { SaveUserInteractor(get(), get()) }
}
val dataModule = module {
    factory<ServiceTesteAndroidV2> { TesteAndroidv2ServiceImpl() }
    single { UserMapper() }
    single { StatementMapper() }
    single { androidContext().getSharedPreferences("teste-android-v2", Context.MODE_PRIVATE) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<StatementsRepository> { StatementsRepositoryImpl(get(), get()) }
}