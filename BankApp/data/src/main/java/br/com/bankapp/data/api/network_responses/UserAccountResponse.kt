package br.com.bankapp.data.api.network_responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserAccountResponse : Serializable {

    @SerializedName("userId")
    var userId: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("bankAccount")
    var bankAccount: String? = null

    @SerializedName("agency")
    var agency: String? = null

    @SerializedName("balance")
    var balance: Double? = null
}