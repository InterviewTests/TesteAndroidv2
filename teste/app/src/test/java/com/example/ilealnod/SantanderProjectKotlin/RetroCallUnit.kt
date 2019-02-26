package com.example.ilealnod.SantanderProjectKotlin

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi.GetCredentials
import com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi.WebServices
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.AccountInfo
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.ClientData
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.UserInput
import com.example.ilealnod.SantanderProjectKotlin.Todos.Login.LoginActivity
import com.example.ilealnod.SantanderProjectKotlin.Todos.Login.LoginAux
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class RetroCallUnit {


    // Testes da Chamada da api / Populando os objetos

    val pass: String = "T@1"
    val user: String = "igor.lealnodari@hotmail.com"

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userImput: UserInput
    lateinit var mainViewAux: LoginAux
    lateinit var service:WebServices

    lateinit var loginResponse: List<AccountInfo>
    lateinit var loginActivity: LoginActivity
    lateinit var clientData :ClientData



    private var BASE_URL: String = "https://bank-app-test.herokuapp.com/api/"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.loginActivity= LoginActivity()
        this.mainViewAux = LoginAux(LoginActivity())
        this.userImput = UserInput(user, pass)
        this.service = WebServices()
        this.loginResponse = listOf(AccountInfo())
        this.clientData = ClientData()

    }

    @Test
    @Throws(IOException::class)
    fun testAccountInfoDataRequest() {
        val mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetCredentials::class.java)
        val call = service.getAccountInfo("statements/1)")
        val response = call.execute()

        assertEquals(200, response.code())
        assertNotNull(response.body()!!.list)

        mockWebServer.shutdown()
    }
    @Test
    fun testClientDataRequest(){

        val mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetCredentials::class.java)
        val call = service.verifyLogin("user","pass")
        val response = call.execute()

        assertEquals(200, response.code())
        assertNotNull(response.body()?.clientData)
        mockWebServer.shutdown()

    }
}
