package com.lucianogiardino.bankapp.login.domain.model

import com.google.gson.annotations.SerializedName

object User {
    var userId : Int = 0
    var name : String? = null
    var bankAccount : Int = 0
    var agency : Int = 0
    var balance : Double =0.0
}