package br.com.bankapp.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class Util {
    companion object {
        fun verificaCaracter(s: String): Boolean{
            var result = false
            for (i in 0 until s.length) {
                if (Character.isDigit(s[i])) {
                    result = true
                    break
                }
            }
            return result
        }

        fun isEmailValid(email: String): Boolean {
            val pattern: Pattern
            val matcher: Matcher

            val EMAIL_PATTERN = ".+@.+\\.[a-z]+"

            pattern = Pattern.compile(EMAIL_PATTERN)
            matcher = pattern.matcher(email)

            return matcher.matches()
        }

        fun isPasswordValid(password: String): Boolean {

            val pattern: Pattern
            val matcher: Matcher

            val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

            pattern = Pattern.compile(PASSWORD_PATTERN)
            matcher = pattern.matcher(password)

            return matcher.matches()
        }

        //Método que valida o CPF
        fun validaCPF(CPF: String): Boolean {
            var CPF = CPF
            CPF = Mask.unmask(CPF)
            if (CPF == "00000000000" || CPF == "11111111111"
                || CPF == "22222222222" || CPF == "33333333333"
                || CPF == "44444444444" || CPF == "55555555555"
                || CPF == "66666666666" || CPF == "77777777777"
                || CPF == "88888888888" || CPF == "99999999999") {
                return false
            }
            val dig10: Char
            val dig11: Char
            var sm: Int
            var i: Int
            var r: Int
            var num: Int
            var peso: Int
            try {
                sm = 0
                peso = 10
                i = 0
                while (i < 9) {
                    num = CPF[i].toInt() - 48
                    sm = sm + num * peso
                    peso = peso - 1
                    i++
                }
                r = 11 - sm % 11
                if (r == 10 || r == 11)
                    dig10 = '0'
                else
                    dig10 = (r + 48).toChar()
                sm = 0
                peso = 11
                i = 0
                while (i < 10) {
                    num = CPF[i].toInt() - 48
                    sm = sm + num * peso
                    peso = peso - 1
                    i++
                }
                r = 11 - sm % 11
                if (r == 10 || r == 11)
                    dig11 = '0'
                else
                    dig11 = (r + 48).toChar()
                return dig10 == CPF[9] && dig11 == CPF[10]
            } catch (erro: Exception) {
                return false
            }

        }
    }
}