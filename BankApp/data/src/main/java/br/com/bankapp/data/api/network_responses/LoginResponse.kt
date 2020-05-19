package br.com.bankapp.data.api.network_responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginResponse : Serializable {

    @SerializedName("userAccount")
    var userAccount: UserAccountResponse? = null
}