package dev.ornelas.banckapp.domain.interactors

import dev.ornelas.banckapp.domain.exceptions.InvalidUserNameException
import dev.ornelas.banckapp.domain.model.datatype.Result

class ValidateUserName {
    operator fun invoke(user: String?): Result<Unit> {

        if (isValidEmail(user) || isValidCPF(user)){
            return Result.success(Unit)
        }

        return Result.error(InvalidUserNameException("O login deve ser um  email ou cpf válido"))
    }

    private fun isValidEmail(user: String?): Boolean {
        if (user != null ) {
            //RFC2828
            val regex = "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?".toRegex()
           return regex.matches(user)
        }
        return false
    }

    private fun calculeDigit(str: String, peso: IntArray): Int {
        var soma = 0
        var indice = str.length - 1
        var digito: Int
        while (indice >= 0) {
            digito = str.substring(indice, indice + 1).toInt()
            soma += digito * peso[peso.size - str.length + indice]
            indice--
        }
        soma = 11 - soma % 11
        return if (soma > 9) 0 else soma
    }

    fun isValidCPF(cpf: String?): Boolean {
        val pesoCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
        if (cpf == null || cpf.length != 11) return false
        val digito1 = calculeDigit(cpf.substring(0, 9), pesoCPF)
        val digito2 = calculeDigit(cpf.substring(0, 9) + digito1, pesoCPF)
        return cpf == cpf.substring(0, 9) + digito1.toString() + digito2.toString()
    }


}