package dev.ornelas.bankapp.data.datasource.api.retrofit.model

import com.google.gson.annotations.SerializedName

data class UserAccountApi (

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