package br.com.rcp.bank.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorDTO(
	@Expose @SerializedName("code")		var		code	: Long?		= null,
	@Expose @SerializedName("message")	var		message	: String?	= null
)