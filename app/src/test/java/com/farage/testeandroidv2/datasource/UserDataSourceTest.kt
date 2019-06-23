package com.farage.testeandroidv2.datasource

import com.farage.testeandroidv2.datasource.retrofit.RetrofitConfiguration
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserDataSourceTest {

    private var retrofit: RetrofitConfiguration = mock()
    private val userDataSource = UserDataSource(retrofit)

    @Test
    fun doUserLogin_callRetrofitInstance() {
        runBlocking {
            userDataSource.doUserLogin("", "")
            verify(retrofit).instance
        }
    }
}