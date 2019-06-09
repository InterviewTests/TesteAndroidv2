package com.schaefer.bankapp.model.statement

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatementModel {
    @Expose
    @SerializedName("title")
    lateinit var title: String
    @Expose
    @SerializedName("desc")
    lateinit var desc: String
    @Expose
    @SerializedName("date")
    lateinit var date: String
    @Expose
    @SerializedName("value")
    var value: Float = 0.0F
}