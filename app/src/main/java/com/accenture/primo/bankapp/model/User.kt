package com.accenture.primo.bankapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName(value = "userId")
    val id:Long,
    @SerializedName(value = "name")
    val name:String,
    @SerializedName(value = "bankAccount")
    val bankAccount:String,
    @SerializedName(value = "agency")
    val agency:String,
    @SerializedName(value = "balance")
    val balance:Float
): Serializable {

}

