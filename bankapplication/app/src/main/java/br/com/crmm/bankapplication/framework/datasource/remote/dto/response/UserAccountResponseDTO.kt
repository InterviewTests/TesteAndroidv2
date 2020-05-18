package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserAccountResponseDTO(
    @SerializedName("userId")
    private val userId: String? = "",
    @SerializedName("name")
    private val name: String? = "",
    @SerializedName("bankAccount")
    private val bankAccount: String? = "",
    @SerializedName("agency")
    private val agency: String? = "",
    @SerializedName("balance")
    private val balance: Double? = 0.0
)