package br.com.bankapp.data.api.network_responses

import com.google.gson.annotations.SerializedName

data class StatementListResponse(
    @SerializedName("statementList")
    var statementList: List<StatementResponse>? = null
)