package br.com.crmm.bankapplication.di.module

fun getModulesList() = listOf(
    utilModule,
    viewModelModule,
    useCaseModule,
    networkModule,
    remoteDataSource,
    repositoryModule
)