package com.example.santandertestev2.domain

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("userId")
    var id : Int? = null

    var name : String? = null
    var bankAccount: Int? = null
    var agency:Int? = null
    var balance: Double? = null

}