package br.com.crmm.bankapplication.infrastructure.di.module

fun getModulesList() = listOf(
    utilModule,
    viewModelModule,
    useCaseModule,
    networkModule
)