package com.schaefer.bankapp.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.user.UserModel

class LoginResult {
    @Expose
    @SerializedName("error")
    lateinit var error: ErrorResult
    @Expose
    @SerializedName("userAccount")
    lateinit var userAccount: UserModel
}
