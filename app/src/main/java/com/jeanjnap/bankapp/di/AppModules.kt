package com.jeanjnap.bankapp.di

import com.jeanjnap.bankapp.ui.login.LoginViewModel
import com.jeanjnap.bankapp.util.ResourcesStringImpl
import com.jeanjnap.domain.boundary.ResourcesString
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {

    val utilsModules = module {
        single<ResourcesString> { ResourcesStringImpl(get()) }
    }

    val viewModelModules = module {
        viewModel { LoginViewModel(get()) }
    }
}
