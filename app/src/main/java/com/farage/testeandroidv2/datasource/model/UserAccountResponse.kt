package com.farage.testeandroidv2.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserAccountResponse(
    @SerializedName("userId") @Expose val userId: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("bankAccount") @Expose val bankAccount: String,
    @SerializedName("agency") @Expose val agency: String,
    @SerializedName("balance") @Expose val balance: Double
) : Serializable