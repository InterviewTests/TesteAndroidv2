package br.com.bank.android.infra

import java.util.*

object CpfUtil {

    fun isValidCpf(cpfValidate: String?): Boolean {
        if (cpfValidate == null) return false

        val cpf = cpfValidate.replace("[^0-9a-zA-Z]+".toRegex(), "")

        if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999" ||
            cpf.length != 11
        ) return false

        val dig10: Char
        val dig11: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int
        return try {
            sm = 0
            peso = 10
            i = 0
            while (i < 9) {
                num = (cpf[i].toInt() - 48)
                sm += num * peso
                peso -= 1
                i++
            }
            r = 11 - sm % 11
            dig10 =
                if (r == 10 || r == 11) '0' else (r + 48).toChar()
            sm = 0
            peso = 11
            i = 0
            while (i < 10) {
                num = (cpf[i].toInt() - 48)
                sm += num * peso
                peso -= 1
                i++
            }
            r = 11 - sm % 11
            dig11 = if (r == 10 || r == 11) '0' else (r + 48).toChar()
            dig10 == cpf[9] && dig11 == cpf[10]
        } catch (_: InputMismatchException) {
            false
        }
    }
}