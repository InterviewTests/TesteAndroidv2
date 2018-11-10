package br.com.santander.android.bank.login.domain.interactor

import br.com.santander.android.bank.login.domain.interactor.LoginFailureUseCase.*
import br.com.santander.android.bank.login.domain.model.UserAccount
import br.com.santander.android.bank.login.domain.model.UserLogin
import br.com.santander.android.bank.login.repository.LoginRepository
import io.reactivex.Observable

internal class LoginInteractor(private val loginRepository: LoginRepository) {

    fun login(login: UserLogin): Observable<UserAccount> {
        return when {
            login.user.isEmpty() -> raise(EmptyUser)
            login.password.isEmpty() -> raise(EmptyPassword)
            !isPasswordContainsUpper(login.password) -> raise(MalformattedPassword)
            !isPasswordContainsDigits(login.password) -> raise(MalformattedPassword)
            !isPasswordContainsSpecialChar(login.password) -> raise(MalformattedPassword)
            else -> loginRepository.login(login)
        }
    }

    fun saveSession(userAccount: UserAccount) = loginRepository
        .saveSession(userAccount)

    fun getSavedSession() = loginRepository.getSession()

    private fun isPasswordContainsUpper(password: String): Boolean {
        return password.contains(UPPER_PATTERN.toRegex())
    }

    private fun isPasswordContainsDigits(password: String): Boolean {
        return password.contains(DIGIT_PATTERN.toRegex())
    }

    private fun isPasswordContainsSpecialChar(password: String): Boolean {
        return password.contains(SPECIAL_PATTERN.toRegex())
    }

    private fun <T> raise(error: Throwable): Observable<T> {
        return Observable.error(error)
    }

    companion object {
        const val SPECIAL_PATTERN = "[^A-Za-z0-9_]"
        const val UPPER_PATTERN = "[A-Z]${"+"}"
        const val DIGIT_PATTERN = "[0-9]${"+"}"
    }

}