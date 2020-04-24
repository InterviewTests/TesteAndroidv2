package com.joaoneto.testeandroidv2.loginscreen.model

import java.io.Serializable

class LoginResponseModel : Serializable {
    var userAccount: UserAccountModel? = null
    var error: Void? = null
}