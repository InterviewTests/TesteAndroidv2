package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.repository.DashboardRepository
import br.com.mdr.testeandroid.repository.IDashboardRepository
import br.com.mdr.testeandroid.repository.ISignInRepository
import br.com.mdr.testeandroid.repository.SignInRepository
import org.koin.dsl.module

/**
 * @author Marlon D. Rocha
 * @since 05/07/2020
 */

val repositoryModule = module {
    single { SignInRepository(get(), get(), get(), get()) as ISignInRepository }
    single { DashboardRepository(get(), get(), get()) as IDashboardRepository }
}