package br.com.bankapp.data.api.network_responses

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("userAccount")
    var userAccount: UserAccountResponse? = null
)