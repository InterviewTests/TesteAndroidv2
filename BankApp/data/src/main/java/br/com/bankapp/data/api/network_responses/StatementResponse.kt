package br.com.bankapp.data.api.network_responses

import com.google.gson.annotations.SerializedName

data class StatementResponse(
    @SerializedName("title")
    var title: String?,
    @SerializedName("desc")
    var description: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("value")
    var value: Double?
)