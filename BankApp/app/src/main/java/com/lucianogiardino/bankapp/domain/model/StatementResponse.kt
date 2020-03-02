package com.lucianogiardino.bankapp.domain.model

import com.google.gson.annotations.SerializedName
import com.lucianogiardino.bankapp.domain.model.Error
import com.lucianogiardino.bankapp.domain.model.Statement

data class StatementResponse(

    @SerializedName("statementList")
    val statementList : ArrayList<Statement>,

    @SerializedName("error")
    val error : Error

)