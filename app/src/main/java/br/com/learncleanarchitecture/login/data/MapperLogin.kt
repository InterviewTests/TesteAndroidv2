package br.com.learncleanarchitecture.login.data

import br.com.learncleanarchitecture.login.data.api.LoginData
import br.com.learncleanarchitecture.login.data.room.LoginEntity
import br.com.learncleanarchitecture.login.presentation.Login

//todo: Fazer um mapper para cada camada.. depois para por em modulos vai precisar disso..
object MapperLogin {
    internal fun loginDataToLogin(loginResponse: LoginData?): Login? {
        loginResponse?.let {
            val login = Login()
            login.userId = loginResponse.userId
            login.name = loginResponse.name
            login.balance = loginResponse.balance
            login.bankAccount = loginResponse.bankAccount
            login.agency = loginResponse.agency

            return login
        } ?: run {
            return null
        }
    }

    fun loginDataToLoginEntity(loginData: LoginData?): LoginEntity? {
        loginData?.let {
            return LoginEntity(it.userId, it.name, it.bankAccount, it.agency, it.balance, it.username, it.pass)
        } ?: run {
            return null
        }
    }

    fun loginEntityToLogin(loginEntity: LoginEntity?): Login? {
        loginEntity?.let {
            val login = Login(it.userId, it.name, it.bankAccount, it.agency, it.balance)

            login.setUsername(it.username!!)
            login.setPass(it.pass!!)

            return login
        } ?: run {
            return null
        }
    }

}