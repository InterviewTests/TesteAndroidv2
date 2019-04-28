package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.interactor.LoginInteractor
import br.com.alex.bankappchallenge.interactor.LoginInteractorContract
import org.koin.dsl.module

val interactorModule = module {
    single<LoginInteractorContract> { LoginInteractor(get(), get()) }
}