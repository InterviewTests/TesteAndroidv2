package com.example.testesantander.domain.model

import com.google.gson.annotations.SerializedName

class Error(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)