package com.accenture.santander.remote.service.login

import com.accenture.santander.entity.Auth
import com.accenture.santander.viewmodel.UserViewModel
import retrofit2.Response

interface IServiceLogin {

    fun login(
        user: UserViewModel, success: (note: Response<Auth?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}