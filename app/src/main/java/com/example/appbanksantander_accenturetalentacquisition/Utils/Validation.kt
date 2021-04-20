package com.example.appbanksantander_accenturetalentacquisition.Utils


import java.util.regex.Matcher
import java.util.regex.Pattern

class Validation {

    fun verifyUser(user: String): Boolean{
        val isCpf = isCPF(user)
        val isEmail = isEmail(user)

        if (isCpf){
            return true
        }
        if (isEmail){
            return true
        }
        return false
    }

    fun verifyPassword(password: String): Boolean{
        val regex = "(?=.*[}{#,.^?~=+\\-_\\/*\\-+.\\|])(?=.*[a-zA-Z])(?=.*[0-9]).{8,}"
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(password)

        val minimumChar = 6
        if (password.length < minimumChar){
            return false
        }
        return matcher.matches()
    }

    fun isEmail(user: String): Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (user.isNotEmpty())
        if (user.matches(emailRegex.toRegex())){
            return true
        }
        return false
    }

    fun isCPF(cpf: String): Boolean {
        val digit11 = 11
        val digit10 = 10

        if (cpf.isEmpty()){
            return false
        }
        if (cpf.contains("^[a-Z]")){
            return false
        }
        val numbers = arrayListOf<Int>()

        cpf.filter {
            it.isDigit()
        }.forEach {
            numbers.add(it.toString().toInt())
        }

        if (numbers.size != digit11){
            return false
        }
        if (numbers.size > digit11){
            return false
        }
        if (numbers.size < digit10){
            return false
        }
        return true
    }
}