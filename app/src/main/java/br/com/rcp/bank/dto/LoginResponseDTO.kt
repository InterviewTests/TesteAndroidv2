package br.com.rcp.bank.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
	@Expose @SerializedName("userAccount")	var		account	: UserAccountDTO,
	@Expose @SerializedName("error")		var		error	: ErrorDTO
)