package br.com.crmm.bankapplication.framework.datasource.remote.abstraction

import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.LoginResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    fun login(
        @Body loginRequestDTO: LoginRequestDTO
    ): Call<LoginResponseDTO>
}