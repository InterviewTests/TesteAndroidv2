package dev.ornelas.bankapp.data.datasource.api.retrofit.model

import com.google.gson.annotations.SerializedName

data class StatementApi(

    @SerializedName("title")
    val title: String,

    @SerializedName("desc")
    val desc: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("value")
    val value: Double

)