package com.example.desafiosantander.utils

object Constants {

    const val REGEX_CPF = "^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$"
    const val REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{4,}\$"

    const val KEY_SAVE_USER = "USER"
    const val KEY_SAVE_USER_EMAIL = "USER_EMAIL"

    const val FORMAT_DATE_SERVER = "yyyy-MM-dd"
    const val FORMAT_DATE_PT_BR = "dd/MM/yyyy"
}