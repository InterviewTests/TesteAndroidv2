package br.com.bankapp.domain.repository

interface LoginRepository {

    suspend fun attemptLogin(user: String, password: String)
}