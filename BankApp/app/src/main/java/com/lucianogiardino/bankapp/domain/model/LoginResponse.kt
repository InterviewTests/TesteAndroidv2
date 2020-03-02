package com.lucianogiardino.bankapp.domain.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("userAccount")
    val userAccount : UserAccount,

    @SerializedName("error")
    val error : Error
)