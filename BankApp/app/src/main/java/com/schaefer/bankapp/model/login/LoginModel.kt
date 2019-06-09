package com.schaefer.bankapp.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginModel(
    @Expose
    @SerializedName("user")
    val user: String,
    @Expose
    @SerializedName("password")
    val password: String
)