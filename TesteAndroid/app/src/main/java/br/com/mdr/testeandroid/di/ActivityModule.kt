package br.com.mdr.testeandroid.di

import com.santander.uma.flow.main.LoadingPresenter
import org.koin.dsl.module

val activityModule = module {
    single { LoadingPresenter() }
}