package br.com.crmm.bankapplication.infrastructure.di.module

fun getModulesList() = listOf(
    validationUtilModule,
    cpfUtilModule,
    passwordUtilModule,
    loginViewModelModule,
    loginUseCaseModule
)