package com.ygorcesar.testeandroidv2.base.common.validations

import android.support.v4.util.PatternsCompat
import br.com.concrete.canarinho.validator.ValidadorCPF
import com.ygorcesar.testeandroidv2.base.common.utils.RegexUtils

object Validations {

    fun hasSpecialCharacter(string: String) = RegexUtils.CHARACTER_SPECIAL.find(string) != null

    fun hasCapitalizedLetter(string: String) = RegexUtils.CHARACTER_CAPITALIZED.find(string) != null

    fun hasNumber(string: String) = RegexUtils.NUMBER.find(string) != null

    fun isCpfValid(cpf: String) = ValidadorCPF.CPF.ehValido(cpf)

    fun isEmailValid(email: String) = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

}
