package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.api.SignInApi
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */

val apiModule = module {
    single { get<Retrofit>().create(SignInApi::class.java) }
}