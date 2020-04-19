package br.com.rcp.bank.dto

import br.com.rcp.bank.database.models.Account
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserAccountDTO(
	@Expose @SerializedName("userId")		var		userID	: Long?		= null,
	@Expose @SerializedName("name")			var		name	: String?	= null,
	@Expose	@SerializedName("bankAccount")	var		account	: String?	= null,		// agency
	@Expose	@SerializedName("agency")		var		agency	: String?	= null,		// account
	@Expose	@SerializedName("balance")		var		balance	: Double?	= null
) {
	fun getAccountEntity(): Account {
		return Account(
			external	= userID!!,
			name 		= name!!,
			account 	= StringBuilder(agency!!).insert(2, ".").insert(agency!!.length, "-").toString(),
			agency 		= account!!,
			balance 	= balance!!
		)
	}
}