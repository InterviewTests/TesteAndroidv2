package com.example.bankapp.pojo.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginPojo {

    @SerializedName("userAccount")
    @Expose
    var userAccount: UserAccount? = null
    @SerializedName("error")
    @Expose
    var error: Error? = null

}
