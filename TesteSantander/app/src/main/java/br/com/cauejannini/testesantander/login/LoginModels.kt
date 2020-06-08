package br.com.cauejannini.testesantander.login

import java.io.Serializable

data class LoginRequest(val user: String, val password: String) {}

class LoginResponseModel {

    var userAccount: UserAccount? = null
    var error: Any? = null
}

class UserAccount: Serializable {

    var userId: Int? = null
    var name: String? = null
    var bankAccount: String? = null
    var agency: String? = null
    var balance: Double? = null
}