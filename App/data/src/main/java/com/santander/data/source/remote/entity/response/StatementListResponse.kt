package com.santander.data.source.remote.entity.response

import com.google.gson.annotations.SerializedName

data class StatementListResponse(

	@field:SerializedName("statementList")
	val statementList: List<StatementResponse> = emptyList(),

	@field:SerializedName("error")
	val error: ErrorResponse? = null
)