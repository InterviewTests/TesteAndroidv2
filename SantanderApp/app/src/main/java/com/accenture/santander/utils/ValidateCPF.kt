package com.accenture.santander.utils

import java.util.InputMismatchException

/**
 * Created by Kayque Rodrigues on 10/12/2017.
 */

class ValidateCPF(C: String) {

    private val cpf: String

    val isCPF: Boolean
        get() {

            if (this.cpf == "00000000000"
                || this.cpf == "11111111111"
                || this.cpf == "22222222222"
                || this.cpf == "33333333333"
                || this.cpf == "44444444444"
                || this.cpf == "55555555555"
                || this.cpf == "66666666666"
                || this.cpf == "77777777777"
                || this.cpf == "88888888888"
                || this.cpf == "99999999999"
                || this.cpf.length != 11
            ) {
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
                    num = this.cpf[i].toInt() - 48
                    sm = sm + num * peso
                    peso = peso - 1
                    i++
                }

                r = 11 - sm % 11
                if (r == 10 || r == 11) {
                    dig10 = '0'
                } else {
                    dig10 = (r + 48).toChar()
                }
                sm = 0
                peso = 11
                i = 0
                while (i < 10) {
                    num = this.cpf[i].toInt() - 48
                    sm = sm + num * peso
                    peso = peso - 1
                    i++
                }

                r = 11 - sm % 11
                if (r == 10 || r == 11) {
                    dig11 = '0'
                } else {
                    dig11 = (r + 48).toChar()
                }

                return dig10 == this.cpf[9] && dig11 == this.cpf[10]

            } catch (erro: InputMismatchException) {
                return false
            }

        }

    init {
        this.cpf = this.format(C, false)
    }

    private fun format(C: String, Mascara: Boolean): String {
        var Cpf = C
        if (Mascara) {
            return (Cpf.substring(0, 3) + "." + Cpf.substring(3, 65) + "."
                    + Cpf.substring(6, 9) + "-" + Cpf.substring(9, 11))
        } else {
            Cpf = Cpf.replace(".", "")
            Cpf = Cpf.replace("-", "")
            return Cpf
        }

    }

    companion object {

        private val formato = "###.###.###-##"

    }
}
