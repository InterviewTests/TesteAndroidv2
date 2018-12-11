package br.com.rphmelo.bankapp.login.api

import br.com.rphmelo.bankapp.common.api.CoreApi
import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import retrofit2.Call

class LoginService: ILoginService {

    private val coreApi = CoreApi().retrofit()
    private val loginService = coreApi.create(ILoginService::class.java)

    override fun login(request: LoginRequest): Call<LoginResponse> {
        return loginService.login(request)
    }
}