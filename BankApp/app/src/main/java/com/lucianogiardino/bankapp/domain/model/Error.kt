package com.lucianogiardino.bankapp.domain.model

import com.google.gson.annotations.SerializedName

data class Error (
    @SerializedName("code")
    val code : Int,

    @SerializedName("message")
    val message : String
)