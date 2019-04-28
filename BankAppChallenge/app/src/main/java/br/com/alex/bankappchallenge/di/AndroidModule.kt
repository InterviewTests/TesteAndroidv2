package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.utils.BankAccountFormatter
import br.com.alex.bankappchallenge.utils.BankAccountFormatterContract
import br.com.alex.bankappchallenge.utils.PasswordValidator
import br.com.alex.bankappchallenge.utils.PasswordValidatorContract
import org.koin.dsl.module

val androidModule = module {
    factory<PasswordValidatorContract> { PasswordValidator() }
    factory<BankAccountFormatterContract> { BankAccountFormatter() }
}