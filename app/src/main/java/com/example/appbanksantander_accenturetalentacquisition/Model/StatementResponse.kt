package com.example.appbanksantander_accenturetalentacquisition.Model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

class StatementResponse {

    @field:SerializedName("statementList")
    lateinit var statementListResult: List<StatementModel>

}