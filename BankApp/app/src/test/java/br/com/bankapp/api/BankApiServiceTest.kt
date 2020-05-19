package br.com.bankapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.bankapp.BaseServiceTest
import br.com.bankapp.data.api.BankAppApiService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class BankApiServiceTest: BaseServiceTest() {

    private lateinit var service: BankAppApiService

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun setup() {
        super.setup()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankAppApiService::class.java)
    }

    @Test
    fun requestLogin_confirmResponse() {
        runBlocking {
            val resultResponse = service.login("test_user", "Test@1")
            val request = mockWebServer.takeRequest()

            assertThat(resultResponse, not(nullValue()))
            assertThat(resultResponse.userAccount, not(nullValue()))
            assertThat(resultResponse.userAccount!!.userId, equalTo(1))
            assertThat(request.path, `is`("/login"))
        }
    }
}