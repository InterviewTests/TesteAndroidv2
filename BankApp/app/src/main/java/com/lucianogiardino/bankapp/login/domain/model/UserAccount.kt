package com.lucianogiardino.bankapp.login.domain.model

import com.google.gson.annotations.SerializedName

data class UserAccount (

    @SerializedName("userId")
    val userId : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("bankAccount")
    val bankAccount : Int,

    @SerializedName("agency")
    val agency : Int,

    @SerializedName("balance")
    val balance : Double
)