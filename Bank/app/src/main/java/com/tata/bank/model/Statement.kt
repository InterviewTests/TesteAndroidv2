package com.tata.bank.model

data class Statement(
    val date: String,
    val desc: String,
    val title: String,
    val value: Int
)