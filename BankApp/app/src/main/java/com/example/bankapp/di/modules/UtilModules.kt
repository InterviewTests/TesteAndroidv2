package com.example.bankapp.di.modules

import com.example.bankapp.util.SessionManager
import org.koin.dsl.module

internal val UtilModules = module {
    single {
        SessionManager(get())
    }
}
