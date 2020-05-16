package com.lucianogiardino.bankapp.domain.usecase

import com.lucianogiardino.bankapp.presentation.login.LoginContract

class ValidateUsernameUseCase: LoginContract.UseCase.ValidateUsername {
    override fun execute(
        listener: LoginContract.Presenter.OnValidateUsernameResponse,
        username: String
    ) {

        if (isEmail(username) || isCPF(username)) {
            listener.onValidateUsernameResponseSuccess(true)
        }else{
            listener.onValidateUsernameResponseFailed("Digite um e-mail ou cpf")
        }
    }

    private fun isEmail(document: String): Boolean{
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
        return EMAIL_REGEX.toRegex().matches(document);

    }
    private fun isCPF(document: String): Boolean {
        if (document.isEmpty()) return false

        val numbers = arrayListOf<Int>()

        document.filter { it.isDigit() }.forEach {
            numbers.add(it.toString().toInt())
        }

        if (numbers.size != 11) return false

        (0..9).forEach { n ->
            val digits = arrayListOf<Int>()
            (0..10).forEach { digits.add(n) }
            if (numbers == digits) return false
        }

        val dv1 = ((0..8).sumBy { (it + 1) * numbers[it] }).rem(11).let {
            if (it >= 10) 0 else it
        }

        val dv2 = ((0..8).sumBy { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
            if (it >= 10) 0 else it
        }

        return numbers[9] == dv1 && numbers[10] == dv2
    }
}