package dev.ornelas.bankapp.data.datasource.api.retrofit.model

import com.google.gson.annotations.SerializedName

data class StatementResponse(

    @SerializedName("statementList")
    val statementList : ArrayList<StatementApi>,

    @SerializedName("error")
    val error : ErrorApi

)