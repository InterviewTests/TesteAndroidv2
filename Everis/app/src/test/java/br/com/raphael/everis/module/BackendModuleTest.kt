package br.com.raphael.everis.module

import br.com.raphael.everis.di.module.BackendModule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import kotlinx.coroutines.runBlocking
import br.com.raphael.everis.extensions.readJSON
import br.com.raphael.everis.remote.BackendService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
open class BackendModuleTest : BackendModule() {

    @Singleton
    override fun providesBackendService(retrofit: Retrofit) = mock<BackendService>{
        // postLogin(any, any)
        on {
            runBlocking { postLogin(any(), any()) }
        } doReturn readJSON("login")

        // getStatements(any)
        on {
            runBlocking { getStatement(any()) }
        } doReturn readJSON("statements")
    }

}