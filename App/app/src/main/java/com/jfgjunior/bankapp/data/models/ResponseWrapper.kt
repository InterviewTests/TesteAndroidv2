package com.jfgjunior.bankapp.data.models

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    @SerializedName(value = "userAccount", alternate = ["statementList"])
    val result: T?,
    val error: ResponseError
)