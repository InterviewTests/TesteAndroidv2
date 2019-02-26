package com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccountInfoResponse {

    // Resposta da chamada

    @SerializedName("statementList")
    @Expose
    var list:List<AccountInfo> ?= null
}