package com.gustavo.bankandroid.datasource.data.user.gson

import com.google.gson.annotations.SerializedName


data class ServerLoginResponse (

	@SerializedName("userAccount") val userAccount : UserAccount,
	@SerializedName("error") val error : Error
)