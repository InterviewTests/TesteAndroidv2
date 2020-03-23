package com.example.ibm_test.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfoData (
    @SerializedName("userId") val userId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("bankAccount") val bankAccount: String,
    @SerializedName("agency") val agency: String,
    @SerializedName("balance") val balance: Float
) : Serializable