package br.com.cauejannini.testesantander.commons.form.validators

/**
 * Created by cauejannini on 23/02/17.
 */
object Validations {


    fun validarSenha(senha: String?): ValidationResult {
        return if (senha != null &&
                senha.isNotEmpty() &&
                // Checar se tem pelo menos um dígito
                senha.matches("(?=.*[0-9]).*".toRegex()) &&

                // Checar se tem pelo menos uma letra maiúscula
                senha.matches("(?=.*[A-Z]).*".toRegex()) &&

                // Checar se tem pelo menos um caractere especial
                senha.matches("(?=.*[~!@#$%^&*()_-]).*".toRegex())) {
            ValidationResult(true, "Senha OK")
        } else {
            ValidationResult(false, "Deve conter um número, uma maiúscula e um caractere especial")
        }
    }

    fun validarUsuario(usuario: String?): ValidationResult {

        return if (validarCpf(usuario) || validarEmail(usuario)) {
            ValidationResult(true, "Usuário OK")
        } else {
            ValidationResult(false, "Usuário deve ser email ou CPF")
        }
    }

    fun validarEmail(email: String?): Boolean {
        return (email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()))
    }

    fun validarCpf(cpfString: String?): Boolean {
        try {
            val cpf = cpfString!!.replace("-", "").replace(".", "")
            if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999") {
                return false
            }
            if (cpf.length < 11) {
                return false
            } else if (cpf.length == 11) {

                // VALIDAR PRIMEIRO DIGITO
                val d1 = cpf[0].toInt() - 48
                val d2 = cpf[1].toInt() - 48
                val d3 = cpf[2].toInt() - 48
                val d4 = cpf[3].toInt() - 48
                val d5 = cpf[4].toInt() - 48
                val d6 = cpf[5].toInt() - 48
                val d7 = cpf[6].toInt() - 48
                val d8 = cpf[7].toInt() - 48
                val d9 = cpf[8].toInt() - 48
                val d10 = cpf[9].toInt() - 48
                val d11 = cpf[10].toInt() - 48
                val totalSum1 = d1 * 10 + d2 * 9 + d3 * 8 + d4 * 7 + d5 * 6 + d6 * 5 + d7 * 4 + d8 * 3 + d9 * 2

                val remainder1 = totalSum1 % 11
                var firstDigitCorret = 0
                if (remainder1 >= 2) {
                    firstDigitCorret = 11 - remainder1
                }
                if (d10 == firstDigitCorret) {

                    // VALIDAR SEGUNDO DÍGITO
                    val totalSum2 =
                        totalSum1 + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + d10 * 2
                    val remainder2 = totalSum2 % 11
                    var secondDigitCorrect = 0
                    if (remainder2 >= 2) {
                        secondDigitCorrect = 11 - remainder2
                    }
                    if (secondDigitCorrect == d11) {
                        return true
                    }
                }
            }
        } catch (ignored: Exception) {
        }
        return false
    }

    fun validarValorMonetario(renda: String): ValidationResult {

        val rendaJustNumbers = renda.replace(".", "").replace("R$", "").trim { it <= ' ' }
        val arrayRenda = rendaJustNumbers.split(",".toRegex()).toTypedArray()
        val rendaSemDecimal = arrayRenda[0]

        return if (rendaSemDecimal.matches("[0-9]+".toRegex())) {
            ValidationResult(true, "Valor ok")
        } else {
            ValidationResult(false, "Valor inválido")
        }
    }
}