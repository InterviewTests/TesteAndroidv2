package br.com.rcp.bank.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatementDTO(
	@Expose @SerializedName("title")	var		title		: String	= "",
	@Expose @SerializedName("desc")		var		description	: String	= "",
	@Expose @SerializedName("date")		var		date		: String	= "",
	@Expose @SerializedName("value")	var		value		: Double	= 0.0
)