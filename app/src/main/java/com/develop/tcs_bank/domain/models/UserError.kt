package com.develop.tcs_bank.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserError (

    @SerializedName("code")
    @Expose val code: Int,

    @SerializedName("message")
    @Expose val message: String

)