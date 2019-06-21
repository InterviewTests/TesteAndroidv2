package com.santander.data.source.remote.entity.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("userAccount")
	val userAccount: UserAccountResponse,

	@field:SerializedName("error")
	val error: ErrorResponse? = null
)