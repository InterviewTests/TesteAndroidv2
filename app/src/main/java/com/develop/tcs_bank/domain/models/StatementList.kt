package com.develop.tcs_bank.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatementList(

    @SerializedName("title")
    @Expose val title: String,

    @SerializedName("desc")
    @Expose val desc: String,

    @SerializedName("date")
    @Expose val date: String,

    @SerializedName("value")
    @Expose val value: Float

)