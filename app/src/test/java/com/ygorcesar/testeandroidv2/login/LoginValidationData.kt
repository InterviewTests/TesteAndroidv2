package com.ygorcesar.testeandroidv2.login

import com.ygorcesar.testeandroidv2.base.common.network.ResponseError
import com.ygorcesar.testeandroidv2.login.data.UserAccountRaw
import com.ygorcesar.testeandroidv2.login.data.UserAccountResponse
import com.ygorcesar.testeandroidv2.login.model.UserAccount

const val cpfValid = "852.656.113-80"
const val cpfInvalid = "852.656.113-81"
const val emailValid = "ygor@gmail.com"
const val emailInvalid = "ygorgmailcom"
const val passwordValid = "123Abc#"
const val passwordInvalidSpecialCharacter = "Abc123"
const val passwordInvalidCapitalizedCharacter = "abc123$"

val USER = UserAccount(1, "Ygor", "12345", "12345", 1000.0)

val USER_RAW = UserAccountResponse(
    UserAccountRaw(1, "Ygor", "12345", "12345", 1000.0),
    null
)

val USER_ERROR = UserAccountResponse(
    null,
    ResponseError(401, "Usuário ou senha inválido")
)