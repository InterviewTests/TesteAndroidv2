package com.accenture.santander.interector.remote.service.login

import com.accenture.santander.viewmodel.User
import com.accenture.santander.entity.Auth
import retrofit2.Response

interface IServiceLogin {

    fun login(
        user: User, success: (note: Response<Auth?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}