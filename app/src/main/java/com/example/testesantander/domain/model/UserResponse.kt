package com.example.testesantander.domain.model

import com.google.gson.annotations.SerializedName

class UserResponse(
    @SerializedName("userAccount")
    val userAccount: UserData,
    @SerializedName("error")
    val error: Error?
)