package br.com.bank.android.login.handler

import br.com.bank.android.exceptions.BusinessError
import br.com.bank.android.login.data.UserAccount

interface LoginHandler {
    fun logged(userAccount: UserAccount)
    fun setLoading(loading: Boolean)
    fun onError(error: BusinessError)
}