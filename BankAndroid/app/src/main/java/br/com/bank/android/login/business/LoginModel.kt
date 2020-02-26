package br.com.bank.android.login.business

import android.util.Patterns
import br.com.bank.android.core.retrofit.auth.IBankAuthService
import br.com.bank.android.exceptions.PasswordInvalid
import br.com.bank.android.exceptions.UserInvalid
import br.com.bank.android.infra.CpfUtil
import br.com.bank.android.infra.RegexUtil
import br.com.bank.android.login.data.UserAccount

class LoginModel(private val bankAuthService: IBankAuthService) : LoginBusiness {

    @Throws(UserInvalid::class)
    override fun validateUser(user: String?) {
        if (user == null) throw UserInvalid()

        val isValidCpf = CpfUtil.isValidCpf(user)
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(user).matches()

        if (!isValidCpf && !isValidEmail) {
            throw UserInvalid()
        }
    }

    @Throws(PasswordInvalid::class)
    override fun validatePassword(password: String?) {
        val isValidPassword = RegexUtil.isValidPassword(password)
        if (!isValidPassword) {
            throw PasswordInvalid()
        }
    }

    override suspend fun onLogin(user: String, password: String): UserAccount {
        val response = bankAuthService.onLogin(user, password)
        return UserAccount(
            response.userId,
            response.name,
            response.bankAccount,
            response.agency,
            response.balance
        )
    }
}