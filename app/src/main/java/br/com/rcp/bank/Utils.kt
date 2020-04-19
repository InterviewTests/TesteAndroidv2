package br.com.rcp.bank

import java.util.regex.Pattern

class Utils {
	companion object {
		fun validatePassword(password: String): Boolean {
			val	pattern	= Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$")
			val	matcher	= pattern.matcher(password)
			return matcher.matches()
		}

		fun validateLogin(username: String): Boolean {
			return username.isNotEmpty()
		}
	}
}