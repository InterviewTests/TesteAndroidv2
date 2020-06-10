package com.qintess.santanderapp.helper

class Validator {
    companion object {
        fun isEmailValid(email: String): Boolean {
            val pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}".toRegex()
            return email.matches(pattern)
        }

        fun isCpfValid(cpf: String): Boolean {
            val numbers = arrayListOf<Int>()

            cpf.filter { it.isDigit() }.forEach {
                numbers.add(it.toString().toInt())
            }

            if (numbers.size != 11) return false

            //repeticao
            (0..9).forEach { n ->
                val digits = arrayListOf<Int>()
                (0..10).forEach { digits.add(n) }
                if (numbers == digits) return false
            }

            //digito 1
            val dv1 = ((0..8).sumBy { (it + 1) * numbers[it] }).rem(11).let {
                if (it >= 10) 0 else it
            }

            val dv2 = ((0..8).sumBy { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
                if (it >= 10) 0 else it
            }

            return numbers[9] == dv1 && numbers[10] == dv2
        }

        fun isPasswordValid(password: String): Boolean {
            if (
                !password.matches(".*[A-Z].*".toRegex()) ||
                !password.matches(".*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*".toRegex()) ||
                !password.matches(".*[0-9].*".toRegex())
            ) {
                return false
            }

            return true
        }
    }
}