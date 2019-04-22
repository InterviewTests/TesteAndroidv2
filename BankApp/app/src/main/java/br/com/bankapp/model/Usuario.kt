package br.com.bankapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Usuario: Serializable {
    @SerializedName("userId")
    @Expose
    var userId: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("bankAccount")
    @Expose
    var bankAccount: String? = null

    @SerializedName("agency")
    @Expose
    var agency: String? = null

    @SerializedName("balance")
    @Expose
    var balance: Double? = null
}