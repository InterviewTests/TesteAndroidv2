package com.earaujo.santander.login

import com.earaujo.santander.repository.models.UserAccountModel

data class LoginActivityModel(
    var userAccount: UserAccountModel
)