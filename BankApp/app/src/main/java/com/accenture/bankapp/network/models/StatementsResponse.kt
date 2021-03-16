package com.accenture.bankapp.network.models

import com.google.gson.annotations.SerializedName

data class StatementsResponse(
    @SerializedName("statementList")
    var statementList: List<Statement>,
    @SerializedName("error")
    var error: Error,
)
