package com.example.bankapp.pojo.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserAccount {

    @SerializedName("userId")
    @Expose
    var userId: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("bankAccount")
    @Expose
    var bankAccount: String? = null
    @SerializedName("agency")
    @Expose
    var agency: String? = null
    @SerializedName("balance")
    @Expose
    var balance: Double? = null

}
