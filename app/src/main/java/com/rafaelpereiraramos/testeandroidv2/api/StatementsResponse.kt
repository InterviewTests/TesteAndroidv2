package com.rafaelpereiraramos.testeandroidv2.api

import com.google.gson.annotations.SerializedName
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO

/**
 * Created by Rafael P. Ramos on 27/11/2018.
 */
data class StatementsResponse (
    @SerializedName("statementList") val statementList: List<StatementTO>,
    @SerializedName("error") val error: Error
)