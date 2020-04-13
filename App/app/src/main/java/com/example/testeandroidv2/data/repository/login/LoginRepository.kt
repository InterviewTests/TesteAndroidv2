package com.example.testeandroidv2.data.repository.login

import com.example.testeandroidv2.domain.login.User

interface LoginRepository {

    fun login(user: User, loginResultCallback: (result: LoginResult) -> Unit)
}