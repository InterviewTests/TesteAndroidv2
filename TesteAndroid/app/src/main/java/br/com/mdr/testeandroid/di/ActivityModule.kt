package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.flow.main.LoadingPresenter
import org.koin.dsl.module

val activityModule = module {
    single { LoadingPresenter() }
}