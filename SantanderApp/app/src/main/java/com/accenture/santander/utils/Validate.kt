package com.accenture.santander.utils

object Validate {

    fun validateLogin(login: String): Boolean {
        return login.matches(Regex("^([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})\$")) ||
                ValidateCPF(login).isCPF
    }

    fun validatePassword(password: String): Boolean {
        return Regex("[:upper:]").containsMatchIn(password) &&
                Regex("[:punct:]").containsMatchIn(password) &&
                Regex("[:alnum:]").containsMatchIn(password)
    }
}