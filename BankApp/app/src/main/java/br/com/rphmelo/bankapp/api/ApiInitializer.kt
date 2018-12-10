package br.com.rphmelo.bankapp.api

import br.com.rphmelo.bankapp.utils.Variables
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInitializer {
    private val retrofit = Retrofit.Builder()
            .baseUrl(Variables.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun loginService() = retrofit.create(LoginService::class.java)


}