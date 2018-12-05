package com.br.natanfelipe.bankapp.view.login

import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.model.UserAccount

class LoginModel {
}

class LoginViewModel {
    var userAccount : UserAccount = UserAccount(0,"","",0,0.0)
}

class LoginRequest() {
    var api = RestApi()
}

class LoginResponse() {
    var userAccount : UserAccount = UserAccount(0,"","",0,0.0)
}