package com.example.bankapp.di.modulos

import com.example.domain.executores.ListarStatementsExecutor
import com.example.domain.executores.RealizarLoginExecutor
import org.koin.dsl.module

internal val ModulosDomain = module {
    factory { ListarStatementsExecutor(repositorio = get()) }
    factory { RealizarLoginExecutor(repositorio = get()) }
}