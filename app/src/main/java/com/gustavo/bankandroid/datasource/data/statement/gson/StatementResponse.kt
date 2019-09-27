package com.gustavo.bankandroid.datasource.data.statement.gson

import com.google.gson.annotations.SerializedName

data class StatementResponse (

	@field:SerializedName("statementList") val statementList : List<StatementList>,
	@field:SerializedName("error") val error : Error
)
