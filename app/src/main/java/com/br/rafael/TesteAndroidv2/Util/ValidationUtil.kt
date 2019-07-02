package com.br.rafael.TesteAndroidv2.Util

import java.util.*
import java.util.regex.Pattern

class ValidationUtil {

    companion object{

        fun isCPF(doc: String): Boolean {

           var CPF = doc.replace(".", "").replace("-", "")

            if (CPF == "00000000000" || CPF == "11111111111" || CPF == "22222222222" || CPF == "33333333333" || CPF == "44444444444" || CPF == "55555555555" || CPF == "66666666666" || CPF == "77777777777" || CPF == "88888888888" || CPF == "99999999999" || CPF.length != 11)
                return false

            val dig10: Char
            val dig11: Char
            var sm: Int
            var i: Int
            var r: Int
            var num: Int
            var peso: Int

            try {
                // Calculo do 1o. Digito Verificador
                sm = 0
                peso = 10
                i = 0
                while (i < 9) {
                    // converte o i-esimo caractere do CPF em um numero:
                    // por exemplo, transforma o caractere '0' no inteiro 0
                    // (48 eh a posicao de '0' na tabela ASCII)
                    num = CPF[i].toInt() - 48
                    sm = sm + num * peso
                    peso = peso - 1
                    i++
                }

                r = 11 - sm % 11
                if (r == 10 || r == 11)
                    dig10 = '0'
                else
                    dig10 = (r + 48).toChar() // converte no respectivo caractere numerico

                // Calculo do 2o. Digito Verificador
                sm = 0
                peso = 11
                i = 0
                while (i < 10) {
                    num = CPF[i].toInt() - 48
                    sm += num * peso
                    peso -= 1
                    i++
                }

                r = 11 - sm % 11
                if (r == 10 || r == 11)
                    dig11 = '0'
                else
                    dig11 = (r + 48).toChar()

                return dig10 == CPF[9] && dig11 == CPF[10]
            } catch (erro: InputMismatchException) {
                return false
            }

        }

        fun isValidEmail(target: CharSequence?): Boolean {
            return if (target == null) false else android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()

        }

        fun validationPassword(password : String) : Boolean {


            return if(password.length >= 2) {
                val letter = Pattern.compile("[A-z]")
                val special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")


                var hasLetter = letter.matcher(password)
                var hasSpecial = special.matcher(password)

                hasLetter.find() && hasSpecial.find()
            } else {
                false
            }


        }
    }
}