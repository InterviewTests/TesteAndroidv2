package com.example.testeandroideveris.feature.login.data.datasource

interface LocalDataSource {

    fun saveUser(user: String)
    fun getUser(): String?
}