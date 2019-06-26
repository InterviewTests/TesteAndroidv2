package com.earaujo.santander.repository.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("userAccount")
    @Expose
    var userAccountModel: UserAccountModel?,
    @SerializedName("error")
    @Expose
    var error: ErrorModel?
)