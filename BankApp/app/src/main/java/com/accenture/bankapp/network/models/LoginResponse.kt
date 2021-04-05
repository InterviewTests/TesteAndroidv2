package com.accenture.bankapp.network.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("userAccount")
    var userAccount: UserAccount,
    @SerializedName("error")
    var error: Error,
)
