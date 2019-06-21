package com.santander.domain.entity.input

sealed class SessionQuery : BaseQuery {
    class SignIn(val user: String, val password: String) : SessionQuery()
    object SignOut : SessionQuery()
}