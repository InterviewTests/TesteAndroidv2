package com.solinftec.desafiosantander_rafaelpimenta.model

import  java.io.Serializable

data class UserAccount(
    val userId : Int,
    val name : String,
    val bankAccount : String,
    val agency : String,
    val balance : Double
) : Serializable
