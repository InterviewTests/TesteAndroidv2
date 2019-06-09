package com.schaefer.bankapp.login_screen.presenter

interface CurrencyPresenterInput {
    fun getListStatement(userId: Int)
    fun logout()
}
