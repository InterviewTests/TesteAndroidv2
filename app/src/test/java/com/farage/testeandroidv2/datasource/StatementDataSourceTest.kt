package com.farage.testeandroidv2.datasource

import com.farage.testeandroidv2.datasource.retrofit.RetrofitConfiguration
import com.farage.testeandroidv2.util.Constants
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit

class StatementDataSourceTest {

    private var retrofit: RetrofitConfiguration = mock()
    private val statementDataSource = StatementDataSource(retrofit)

    @Test
    fun getAllStatements_callRetrofitInstance() {
        runBlocking {
            whenever(retrofit.instance).then { Retrofit.Builder().build() }
            statementDataSource.getAllStatements(1)
            verify(retrofit).instance
        }
    }

}