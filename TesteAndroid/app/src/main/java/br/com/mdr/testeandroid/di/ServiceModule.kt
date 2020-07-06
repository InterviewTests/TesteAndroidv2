package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.service.ISignInService
import br.com.mdr.testeandroid.service.SignInService
import org.koin.dsl.module

/**
 * @author Marlon D. Rocha
 * @since 05/07/2020
 */

val serviceModule = module {
    single { SignInService(get()) as ISignInService }
}