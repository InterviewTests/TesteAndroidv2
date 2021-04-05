package com.accenture.bankapp

import com.accenture.bankapp.network.api.RetrofitBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NetworkUnitTest {
    @Test
    fun apiService_shouldNot_beNull() {
        val apiService = RetrofitBuilder.apiService

        Assert.assertNotNull(apiService)
    }

    @Test
    fun requestLogin_should_retrieveValidUser() {
        val apiService = RetrofitBuilder.apiService

        runBlocking {
            try {
                val response = apiService.requestLogin("test_user", "Test@1")

                Assert.assertTrue(response.isSuccessful)
                Assert.assertNotNull(response.body()!!.userAccount.name)
            } catch (t: Throwable) {
                throw t
            }
        }
    }

    @Test
    fun getStatements_should_retrieveValidStatements() {
        val apiService = RetrofitBuilder.apiService

        runBlocking {
            try {
                val response = apiService.getStatements(1)

                Assert.assertTrue(response.isSuccessful)
                Assert.assertFalse(response.body()!!.statementList.isEmpty())
            } catch (t: Throwable) {
                throw t
            }
        }
    }
}