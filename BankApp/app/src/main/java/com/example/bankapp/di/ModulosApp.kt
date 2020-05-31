package com.example.bankapp.di

import com.example.bankapp.di.modulos.ModulosData
import com.example.bankapp.di.modulos.ModulosDomain
import com.example.bankapp.di.modulos.ModulosUI
import com.example.bankapp.di.modulos.ModulosUtil
import org.koin.core.module.Module

object ModulosApp {
    fun obterModulos() : List<Module> = listOf(
        ModulosUI,
        ModulosData,
        ModulosDomain,
        ModulosUtil
    )
}