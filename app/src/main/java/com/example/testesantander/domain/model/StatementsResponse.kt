package com.example.testesantander.domain.model

import com.google.gson.annotations.SerializedName

class StatementsResponse(
    @SerializedName("statementList")
    val statementList: Array<StatementsData>,
    @SerializedName("error")
    val error: Error?
)