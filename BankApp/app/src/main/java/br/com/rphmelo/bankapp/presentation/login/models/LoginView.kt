package br.com.rphmelo.bankapp.presentation.login.models

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setUserError()
    fun setPasswordError()
    fun navigateToCurrencyPage()
}