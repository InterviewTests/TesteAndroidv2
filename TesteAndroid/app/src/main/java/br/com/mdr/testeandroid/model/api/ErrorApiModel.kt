package br.com.mdr.testeandroid.model.api

import com.google.gson.annotations.SerializedName

data class ErrorApiModel(
    @SerializedName("error_code")
    val errorCode: Int? = null,
    val message: String? = null,
    val param: String? = null
)