package com.rafaelpereiraramos.testeandroidv2.api

import com.google.gson.annotations.SerializedName
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO

/**
 * Created by Rafael P. Ramos on 26/11/2018.
 */
data class LoginResponse(
    @SerializedName("userAccount") val userAccount: UserTO,
    @SerializedName("error") val error: Error
)