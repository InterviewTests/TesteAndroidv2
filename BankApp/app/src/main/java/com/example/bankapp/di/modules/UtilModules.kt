package com.example.bankapp.di.modules

import com.example.bankapp.util.GerenciadorSessao
import org.koin.dsl.module

internal val UtilModules = module {
    single {
        GerenciadorSessao(get())
    }
}
