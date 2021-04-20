package com.example.appbanksantander_accenturetalentacquisition.Model

import com.google.gson.annotations.SerializedName

class UserAccountModel {

    @SerializedName("userId")
    var userId: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("bankAccount")
    var bankAccount: String = ""

    @SerializedName("agency")
    var agency: String = ""

    @SerializedName("balance")
    var balance: Int = 0

}