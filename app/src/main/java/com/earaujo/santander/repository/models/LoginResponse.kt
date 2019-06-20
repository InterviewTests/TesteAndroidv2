package com.earaujo.santander.repository.models

data class LoginResponse(
    var userAccountModel: UserAccountModel,
    var error: ErrorModel?
)