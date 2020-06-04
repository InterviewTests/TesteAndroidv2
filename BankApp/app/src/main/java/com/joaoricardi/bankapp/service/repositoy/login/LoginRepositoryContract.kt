package com.joaoricardi.bankapp.service.repositoy.login

import com.joaoricardi.bankapp.models.login.LoginResponse
import kotlinx.coroutines.Deferred

interface LoginRepositoryContract {

    fun login(
        email:String,
        password: String
    ): Deferred<LoginResponse>
}