package com.example.appbanksantander_accenturetalentacquisition.Model

import com.google.gson.annotations.SerializedName

class LoginResponse {

    @field:SerializedName("userAccount")
    lateinit var userAccountResult: List<UserAccountModel>

}