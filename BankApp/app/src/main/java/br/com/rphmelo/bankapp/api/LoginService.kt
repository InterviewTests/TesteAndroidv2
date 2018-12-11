package br.com.rphmelo.bankapp.api

import br.com.rphmelo.bankapp.models.LoginRequest
import br.com.rphmelo.bankapp.models.LoginResponse
import br.com.rphmelo.bankapp.utils.Variables
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginService: ILoginService {

    private val retrofit = Retrofit.Builder()
            .baseUrl(Variables.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val loginService = retrofit.create(ILoginService::class.java)

    override fun login(request: LoginRequest): Call<LoginResponse> {
        return loginService.login(request)
    }
}