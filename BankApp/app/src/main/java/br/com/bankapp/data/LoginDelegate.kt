package br.com.bankapp.data

interface LoginDelegate {
    fun onLoginResult(loginRes: LoginRes)
}