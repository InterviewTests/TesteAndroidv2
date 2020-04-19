package br.com.rcp.bank.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatementListDTO(
	@Expose @SerializedName("statementList")	var		list	: List<StatementDTO>
	// Ignore errors
	// @Expose @SerializedName("error")			var		error	: ErrorDTO
)