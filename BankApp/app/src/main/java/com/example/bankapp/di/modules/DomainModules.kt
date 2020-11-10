package com.example.bankapp.di.modules

import com.example.domain.executores.ListarStatementsExecutor
import com.example.domain.executores.RealizarLoginExecutor
import org.koin.dsl.module

internal val DomainModules = module {
    factory { ListarStatementsExecutor(repositorio = get()) }
    factory { RealizarLoginExecutor(repositorio = get()) }
}