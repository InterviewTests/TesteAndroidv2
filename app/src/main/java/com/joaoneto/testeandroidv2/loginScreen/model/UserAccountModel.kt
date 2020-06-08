package com.joaoneto.testeandroidv2.loginScreen.model

import java.io.Serializable

class UserAccountModel : Serializable {
    var userId: Int? = null
    var name: String? = null
    var bankAccount: String? = null
    var agency: String? = null
    var balance: Double? = null
}