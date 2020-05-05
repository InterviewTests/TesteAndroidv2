package com.tata.bank.login

object CredentialsValidatorWorker {

    fun isUserValid(user: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*\$")
        val cpfRegex = Regex("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}\$")
        return emailRegex.containsMatchIn(user) || cpfRegex.containsMatchIn(user)
    }

    fun isPasswordValid(password: String): Boolean {
        // Regex to validate if the password has at least
        // one uppercasae letter, one specoal chatacter and one number
        val passwordRegex = Regex("(?=.*[A-Z])(?=.*[0-9])(?=.*?[#?!@\$%^&*-])")
        return passwordRegex.containsMatchIn(password)
    }
}