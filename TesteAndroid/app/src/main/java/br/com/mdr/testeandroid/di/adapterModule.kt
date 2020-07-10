package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.adapter.StatementAdapter
import org.koin.dsl.module

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */

val adapterModule = module {
    single { StatementAdapter() }
}