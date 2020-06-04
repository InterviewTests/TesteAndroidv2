package com.joaoricardi.bankapp.service.repositoy.login

import com.joaoricardi.bankapp.models.login.LoginResponse
import com.joaoricardi.bankapp.service.RetrofitService
import com.joaoricardi.bankapp.service.api.login.LoginApi
import kotlinx.coroutines.Deferred

class LoginRepository : LoginRepositoryContract{

    val loginApi: LoginApi = RetrofitService().getLoginApi()

    override fun login(email: String, password: String): Deferred<LoginResponse> {
        return loginApi.login(
            email,password
        )
    }

}