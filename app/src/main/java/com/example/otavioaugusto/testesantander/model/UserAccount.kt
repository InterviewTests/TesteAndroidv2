package com.example.otavioaugusto.testesantander.model

import com.google.gson.annotations.SerializedName

data class UserAccount(@SerializedName("userId")
                       val userId: Int = 0,
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("bankAccount")
                       val bankAccount: String = "",
                       @SerializedName("agency")
                       val agency: String = "",
                       @SerializedName("balance")
                       val balance: Double = 0.0)