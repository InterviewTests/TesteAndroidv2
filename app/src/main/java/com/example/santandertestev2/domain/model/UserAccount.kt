package com.example.santandertestev2.domain.model.controller

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserAccount: Serializable {

    @SerializedName("userId")
    var id : Int? = null

    var name : String? = null
    var bankAccount: Int? = null
    var agency:Int? = null
    var balance: Double? = null

}