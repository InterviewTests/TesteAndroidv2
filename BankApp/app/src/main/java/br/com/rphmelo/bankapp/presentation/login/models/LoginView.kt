package br.com.rphmelo.bankapp.presentation.login.models

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setUserEmptyError()
    fun setPasswordEmptyError()
    fun setUserInvalidError()
    fun setPasswordInvalidError()
    fun navigateToCurrencyPage()
}