package br.com.bank.android.login.business

import br.com.bank.android.exceptions.PasswordInvalid
import br.com.bank.android.exceptions.UserInvalid
import br.com.bank.android.login.data.UserAccount

interface LoginBusiness {
    @Throws(UserInvalid::class)
    fun validateUser(user: String?)

    @Throws(PasswordInvalid::class)
    fun validatePassword(password: String?)

    suspend fun onLogin(user: String, password: String) : UserAccount
}