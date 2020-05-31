package com.example.bankapp.di.modulos

import com.example.bankapp.util.GerenciadorSessao
import org.koin.dsl.module

internal val ModulosUtil = module {
    single {
        GerenciadorSessao(get())
    }
}
