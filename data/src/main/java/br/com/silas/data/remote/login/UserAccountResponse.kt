package br.com.silas.data.remote.login

import com.google.gson.annotations.SerializedName

data class UserAccountResponse(
    @SerializedName("userId") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("bankAccount") val bankAccount: String?,
    @SerializedName("agency") val agency: String?,
    @SerializedName("balance") val balance: Double
)
