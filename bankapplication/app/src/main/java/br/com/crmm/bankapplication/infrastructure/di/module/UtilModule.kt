package br.com.crmm.bankapplication.infrastructure.di.module

import br.com.crmm.bankapplication.util.CPFUtil
import br.com.crmm.bankapplication.util.PasswordUtil
import br.com.crmm.bankapplication.util.ValidationUtil
import org.koin.dsl.module

val validationUtilModule = module {
    single { ValidationUtil(get(), get()) }
}

val cpfUtilModule = module {
    single { CPFUtil() }
}

val passwordUtilModule = module {
    single { PasswordUtil() }
}