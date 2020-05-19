package br.com.bankapp.data.api.network_responses

import com.google.gson.annotations.SerializedName

class StatementResponse {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("desc")
    var description: String? = null

    @SerializedName("date")
    var date: String? = null

    @SerializedName("value")
    var value: Double? = null
}