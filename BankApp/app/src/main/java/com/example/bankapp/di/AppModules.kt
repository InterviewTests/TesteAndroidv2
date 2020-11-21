package com.example.bankapp.di

import com.example.bankapp.di.modules.*
import org.koin.core.module.Module

object AppModules {
    fun getModules() : List<Module> = listOf(
        UiModules,
        NetworkingModules,
        DomainModules,
        UtilModules
    )
}