package pt.felipegouveia.bankapp.data.common

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("message")
    val message: String? = null
)