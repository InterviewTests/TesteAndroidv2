package br.com.alex.bankappchallenge.repository

import br.com.alex.bankappchallenge.network.BankAPI
import br.com.alex.bankappchallenge.network.model.LoginRequest
import br.com.alex.bankappchallenge.network.model.UserAccount
import com.orhanobut.hawk.Hawk

private const val USER = "USER"

class LoginRepository(
    private val bankAPI: BankAPI
) : LoginRepositoryContract {

    private var userAccount: UserAccount? = null

    override fun saveUserLogin(user: String) {
        Hawk.put(USER, user)
    }

    override fun getUserLogin(): String = Hawk.get(USER, "")

    override fun login(loginRequest: LoginRequest) = bankAPI.login(loginRequest)

    override fun getUserAccount() = userAccount!!

    override fun saveUserAccount(userAccount: UserAccount) {
        this.userAccount = userAccount
    }

    override fun logout() {
        userAccount = null
    }
}