package com.santander.data.source.remote.entity.response

import com.google.gson.annotations.SerializedName

data class UserAccountResponse(

	@field:SerializedName("bankAccount")
	val bankAccount: String? = null,

	@field:SerializedName("agency")
	val agency: String? = null,

	@field:SerializedName("balance")
	val balance: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)