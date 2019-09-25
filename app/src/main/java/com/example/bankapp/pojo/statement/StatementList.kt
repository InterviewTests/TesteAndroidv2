package com.example.bankapp.pojo.statement

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatementList {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("desc")
    @Expose
    var desc: String? = null
    @SerializedName("date")
    @Expose
    var date: String? = null
    @SerializedName("value")
    @Expose
    var value: Double? = null

}
