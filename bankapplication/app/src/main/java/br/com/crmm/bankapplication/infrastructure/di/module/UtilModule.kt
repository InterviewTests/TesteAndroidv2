package br.com.crmm.bankapplication.infrastructure.di.module

import br.com.crmm.bankapplication.util.CPFUtil
import br.com.crmm.bankapplication.util.PasswordUtil
import br.com.crmm.bankapplication.util.ValidationUtil
import org.koin.dsl.module

val utilModule = module {
    single { provideValidationUtil(get(), get()) }
    single { provideCpfUtil() }
    single { providePasswordUtil() }
}

fun provideValidationUtil(
    cpfUtil: CPFUtil,
    passwordUtil: PasswordUtil
): ValidationUtil{
    return ValidationUtil(cpfUtil, passwordUtil)
}

fun provideCpfUtil() = CPFUtil()

fun providePasswordUtil() = PasswordUtil()