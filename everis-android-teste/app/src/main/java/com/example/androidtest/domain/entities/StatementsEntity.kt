package com.example.androidtest.domain.entities

import com.google.gson.annotations.SerializedName

class StatementsEntity(
    @SerializedName("title") var title: String,
    @SerializedName("desc") var desc: String,
    @SerializedName("date") var date: String,
    @SerializedName("value") var value: Double
)

//{
//    "title": "Pagamento",
//    "desc": "Conta de luz",
//    "date": "2018-08-15",
//    "value": -50
//}