package com.gustavo.bankandroid.features.login.data.gson

import com.google.gson.annotations.SerializedName


data class ServerLoginResponse (

	@SerializedName("userAccount") val userAccount : UserAccount,
	@SerializedName("error") val error : Error
)