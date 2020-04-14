package pt.felipegouveia.bankapp.data.login.model

import com.google.gson.annotations.SerializedName
import pt.felipegouveia.bankapp.data.common.Error

data class LoginData(
    @SerializedName("userAccount")
    val userAccount: UserAccountData?,
    @SerializedName("error")
    val error: Error?
)