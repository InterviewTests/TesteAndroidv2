package dev.ornelas.bankapp.data.datasource.api.retrofit.model

import com.google.gson.annotations.SerializedName

data class ErrorApi (
    @SerializedName("code")
    val code : Int?,

    @SerializedName("message")
    val message : String?
)