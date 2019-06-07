package com.zuptest.santander.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("userAccount") val accountResponse: AccountResponse
)

data class AccountResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("holder") val userName: String,
    @SerializedName("bankAccount") val bankAccount: String,
    @SerializedName("agency") val bankAgency: String,
    @SerializedName("balance") val balance: Double
)