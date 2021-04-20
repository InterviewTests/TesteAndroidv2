package com.example.appbanksantander_accenturetalentacquisition.Model

import com.google.gson.annotations.SerializedName

class StatementModel {

    @SerializedName("title")
    var title: String = ""

    @SerializedName("desc")
    var description: String = ""

    @SerializedName("date")
    var date: String = ""

    @SerializedName("value")
    var value: Float = 0.0F



}