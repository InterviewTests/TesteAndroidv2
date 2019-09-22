package com.gustavo.bankandroid.entity

data class UserInfo(
    val id:Int,
    val username:String,
    val password:String,
    val name:String,
    val account:String,
    val balance:Long
)