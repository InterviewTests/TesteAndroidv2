package com.example.bankapp.pojo.statement

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatementPojo {

    @SerializedName("statementList")
    @Expose
    var statementList: List<StatementList>? = null
    @SerializedName("error")
    @Expose
    var error: Error? = null

}
