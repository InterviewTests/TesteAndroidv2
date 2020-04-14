package pt.felipegouveia.bankapp.data.login.model

import com.google.gson.annotations.SerializedName

data class UserAccountData(
    @SerializedName("userId")
    val userId: Int? = -1,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("bankAccount")
    val bankAccount: String? = null,
    @SerializedName("agency")
    val agency: String? = null,
    @SerializedName("balance")
    val balance: Double? = 0.0
)