package com.farage.testeandroidv2.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("userAccount")
    @Expose
    val userAccountResponse: UserAccountResponse
)