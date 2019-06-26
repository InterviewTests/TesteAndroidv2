package com.earaujo.santander.login

import com.earaujo.santander.util.isValidPassword
import com.earaujo.santander.util.isValidUsername

class LoginValidator {
    fun isValidUsername(s: String) = s.isValidUsername()
    fun isValidPassword(s: String) = s.isValidPassword()
}