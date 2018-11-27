package com.rafaelpereiraramos.testeandroidv2.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Rafael P. Ramos on 27/11/2018.
 */
data class Error (
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)