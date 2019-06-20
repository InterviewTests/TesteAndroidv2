package com.accenture.santander.entity

data class Statement(
    var title: String = "",
    var desc: String = "",
    var date: String = "",
    var value: Double = 0.0
)