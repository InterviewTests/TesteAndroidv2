package com.gustavo.bankandroid.data.gson

import com.google.gson.annotations.SerializedName

data class StatementResponse (

	@SerializedName("userStatementItem") val statementList : List<StatementList>,
	@SerializedName("error") val error : Error
)
