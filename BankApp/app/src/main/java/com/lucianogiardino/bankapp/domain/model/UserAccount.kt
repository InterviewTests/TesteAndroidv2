package com.lucianogiardino.bankapp.domain.model

import com.google.gson.annotations.SerializedName

data class UserAccount (

    @SerializedName("userId")
    val userId : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("agency")
    val bankAccount : Int,

    @SerializedName("bankAccount")
    val agency : Int,

    @SerializedName("balance")
    val balance : Double
)