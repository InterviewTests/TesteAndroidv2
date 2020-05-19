package br.com.bankapp.data.api.network_responses

import com.google.gson.annotations.SerializedName


data class UserAccountResponse(
    @SerializedName("userId")
    var userId: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("bankAccount")
    var bankAccount: String?,
    @SerializedName("agency")
    var agency: String?,
    @SerializedName("balance")
    var balance: Double?
)