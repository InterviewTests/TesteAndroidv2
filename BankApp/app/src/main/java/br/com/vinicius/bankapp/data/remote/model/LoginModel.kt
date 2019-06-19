package br.com.vinicius.bankapp.data.remote.model

import br.com.vinicius.bankapp.domain.User

data class LoginModel(
    val agency: String,
    val balance: Double,
    val bankAccount: String,
    val name: String,
    val userId: Int
) {
    fun toDomain(username:String, password:String): User =
        User(username, password, agency, balance, bankAccount, name, userId)

}