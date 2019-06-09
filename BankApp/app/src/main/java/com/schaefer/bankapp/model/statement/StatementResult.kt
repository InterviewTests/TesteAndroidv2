package com.schaefer.bankapp.model.statement

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.schaefer.bankapp.model.ErrorResult

class StatementResult {
    @Expose
    @SerializedName("statementList")
    lateinit var list: List<StatementModel>
    @Expose
    @SerializedName("error")
    lateinit var errors: ErrorResult
}