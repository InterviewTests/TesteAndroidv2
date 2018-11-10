package br.com.santander.android.bank.login.repository

import android.content.SharedPreferences
import br.com.santander.android.bank.login.domain.model.Account
import br.com.santander.android.bank.login.domain.model.UserAccount
import br.com.santander.android.bank.login.domain.model.UserLogin
import com.google.gson.Gson
import io.reactivex.Observable

internal class LoginRepository(private val loginService: LoginService,
                               private val preferences: SharedPreferences) {

    fun login(userLogin: UserLogin): Observable<UserAccount> {
        return loginService.login(userLogin)
    }

    fun saveSession(userAccount: UserAccount) {
        with(preferences.edit()) {
            putString(SESSION_ACCOUNT_KEY, Gson().toJson(userAccount.account))
            apply()
        }
    }

    fun getSession(): Account? {
        val rawValue = preferences.getString(SESSION_ACCOUNT_KEY, DEFAULT_EMPTY_SESSION)
        return if (!rawValue.isNullOrEmpty()) {
            Gson().fromJson(rawValue, Account::class.java)
        } else null
    }

    companion object {
        private const val SESSION_ACCOUNT_KEY = "account"
        private const val DEFAULT_EMPTY_SESSION = ""
    }
}