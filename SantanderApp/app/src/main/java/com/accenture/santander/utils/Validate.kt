package com.accenture.santander.utils

object Validate {

    fun validateLogin(login: String): Boolean {
        return login.matches(Regex("^([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})\$")) ||
                ValidateCPF(login).isCPF
    }

    fun validatePassword(password: String): Boolean {
        return Regex("[A-Z]").containsMatchIn(password) &&
                Regex("[!\"\\#\$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]").containsMatchIn(password) &&
                Regex("[a-zA-Z0-9]").containsMatchIn(password)
    }
}