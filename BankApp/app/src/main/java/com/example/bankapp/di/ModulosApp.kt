package com.example.bankapp.di

import org.koin.core.module.Module

object ModulosApp {
    fun obterModulos() : List<Module> = listOf(
        ModulosUI,
        ModulosData,
        ModulosDomain,
        ModulosUtil
    )
}