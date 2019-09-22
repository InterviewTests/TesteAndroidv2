package com.gustavo.bankandroid.statements.data.gson

import com.google.gson.annotations.SerializedName

data class StatementResponse (

	@SerializedName("userStatementItem") val statementList : List<StatementList>,
	@SerializedName("error") val error : Error
)
