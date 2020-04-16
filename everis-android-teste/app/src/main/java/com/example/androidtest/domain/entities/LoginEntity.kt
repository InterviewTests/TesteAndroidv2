package com.example.androidtest.domain.entities

import com.google.gson.annotations.SerializedName

class LoginEntity(
//    var user: String? = null,
//    var password: String? = null
    @SerializedName("user") var user: String,
    @SerializedName("password") var password: String
)


