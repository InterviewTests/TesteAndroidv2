package com.santander.data.source.remote.entity.response

import com.google.gson.annotations.SerializedName

data class StatementResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("value")
	val value: Double? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)