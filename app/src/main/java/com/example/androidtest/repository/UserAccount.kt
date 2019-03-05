package com.example.androidtest.repository

import com.google.gson.annotations.SerializedName


data class UserAccount(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double,
    var credentials: LoginData? = null
) {

    constructor(raw: AccountRaw, credentials: LoginData? = null) : this(
        userId = raw.userId!!,
        name = raw.name!!,
        bankAccount = raw.bankAccount!!,
        agency = raw.agency!!,
        balance = raw.balance!!,
        credentials = credentials
    )
}

data class LoginData(
    val user: String,
    val pass: String
)

data class AccountRaw(
    @SerializedName("agency") val agency: String?,
    @SerializedName("balance") val balance: Double?,
    @SerializedName("bankAccount") val bankAccount: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("userId") val userId: Int?
)





