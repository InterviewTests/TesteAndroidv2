package com.example.androidtest.repository

import com.google.gson.annotations.SerializedName


data class Account(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
) {
    constructor(raw: AccountRaw) : this(
        userId = raw.userId!!,
        name = raw.name!!,
        bankAccount = raw.bankAccount!!,
        agency = raw.agency!!,
        balance = raw.balance!!
    )
}

data class AccountRaw(
    @SerializedName("agency") val agency: String?,
    @SerializedName("balance") val balance: Double?,
    @SerializedName("bankAccount") val bankAccount: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("userId") val userId: Int?
)





