package com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias

import com.google.gson.annotations.SerializedName

class AccountInfo {

    //Dependencia de Informações da conta

    @SerializedName("title")
    var title: String? = null
    @SerializedName("desc")
    var desc: String? = null
    @SerializedName("date")
    var date: String? = null
    @SerializedName("value")
    var value: Double? = null

}
