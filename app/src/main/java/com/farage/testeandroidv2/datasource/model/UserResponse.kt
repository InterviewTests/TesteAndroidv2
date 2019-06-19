package com.farage.testeandroidv2.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse (
    @SerializedName("userAccount")
    @Expose
    val userAccountResponse: UserAccountResponse
) : Serializable