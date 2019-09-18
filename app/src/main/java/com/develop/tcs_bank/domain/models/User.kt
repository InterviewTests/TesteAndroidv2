package com.develop.tcs_bank.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("user")
    @Expose val user: String,

    @SerializedName("password")
    @Expose val password: String

)