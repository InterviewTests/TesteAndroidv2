package com.example.testeandroideveris.feature.login.data.datasource

interface LoginLocalDataSource {

    fun saveUser(user: String)
    fun getUser(): String?
}