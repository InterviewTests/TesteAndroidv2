package dev.ornelas.bankapp.data.datasource.api.retrofit.model

import com.google.gson.annotations.SerializedName

data class LoginResponseApi (

    @SerializedName("userAccount")
    val userAccount : UserAccountApi,

    @SerializedName("error")
    val error : ErrorApi
)