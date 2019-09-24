package com.gustavo.bankandroid.entity

data class UserInfo(
    val userId : Int,
    val name : String,
    val bankAccount : Int,
    val agency : Int,
    val balance : Double
)