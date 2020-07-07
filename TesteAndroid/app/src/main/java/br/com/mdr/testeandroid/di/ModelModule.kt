package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.Statement
import br.com.mdr.testeandroid.model.business.User
import org.koin.dsl.module

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */

val modelModule = module {

    // Business
    factory { User() }
    single { Statement() }

    // Api
    factory { UserApiModel() }
}