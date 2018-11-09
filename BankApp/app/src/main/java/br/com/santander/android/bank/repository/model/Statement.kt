package br.com.santander.android.bank.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

data class Statement(
    @SerializedName("title")
    val title: String,

    @SerializedName("desc")
    val description: String,

    @SerializedName("date")
    val date: Date,

    @SerializedName("value")
    val value: Double
): Serializable