package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserAccountResponseDTO(
    @SerializedName("userId")
    val userId: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("bankAccount")
    val bankAccount: String? = "",
    @SerializedName("agency")
    val agency: String? = "",
    @SerializedName("balance")
    val balance: Double? = 0.0
)