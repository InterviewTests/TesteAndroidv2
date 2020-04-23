package com.paulokeller.bankapp.models

import com.google.gson.annotations.SerializedName


class Account(@SerializedName("userAccount") val userAccount: UserAccount)

class UserAccount(
    @SerializedName("userId") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("bankAccount") val bankAccount: String,
    @SerializedName("agency") val agency: String,
    @SerializedName("balance") val balance: Double
)
