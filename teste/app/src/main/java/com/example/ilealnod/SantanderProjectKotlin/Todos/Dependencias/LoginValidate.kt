package com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias

import java.util.regex.Pattern

@Suppress("NAME_SHADOWING")
  class LoginValidate {

// Classe usada para validação do Login

    fun isValid(cpfEmail: String): Boolean {
        return isValidEmail(cpfEmail) || isValidCPF(cpfEmail)
    }

    fun checkPassword(password: String): Boolean {
        println("::::::::::::::>$password")
        val number = Pattern.compile("[0-9 ]")
        val uperCase = Pattern.compile("[A-Z ]")
        val characters = Pattern.compile("[!@#$%&*_]")

        return if (!number.matcher(password).find()) {
            false
        } else if (!uperCase.matcher(password).find()) {
            false
        } else characters.matcher(password).find()
    }


        private val pesoCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)

        private fun calcularDigito(str: String, peso: IntArray): Int {
            var soma = 0
            var indice = str.length - 1
            var digito: Int
            while (indice >= 0) {
                digito = Integer.parseInt(str.substring(indice, indice + 1))
                soma += digito * peso[peso.size - str.length + indice]
                indice--
            }
            soma = 11 - soma % 11
            return if (soma > 9) 0 else soma
        }

        private fun padLeft(text: String, character: Char): String {
            return String.format("%11s", text).replace(' ', character)
        }

        fun isValidCPF(cpf: String?): Boolean {
            var cpf = cpf
            cpf = cpf!!.trim { it <= ' ' }.replace("", "").replace("-", "")
            if (cpf.length != 11)
                return false

            for (j in 0..9)
                if (padLeft(Integer.toString(j), Character.forDigit(j, 10)) == cpf)
                    return false

            val digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF)
            val digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF)
            return cpf == cpf.substring(0, 9) + digito1.toString() + digito2.toString()
    }

        fun isValidEmail(email: String?): Boolean {
            if (email == null){
                return false
            }
            else{
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        }

}