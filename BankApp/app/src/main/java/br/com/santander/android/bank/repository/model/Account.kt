package br.com.santander.android.bank.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Account(
    @SerializedName("userId")
    val userId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("bankAccount")
    val bankAccount: String,

    @SerializedName("agency")
    val agency: String,

    @SerializedName("balance")
    val balance: Double
): Serializable