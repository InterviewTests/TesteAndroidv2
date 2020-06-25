package br.com.cauejannini.testesantander.commons.form.validators

import br.com.cauejannini.testesantander.commons.Utils

/**
 * Created by cauejannini on 14/03/2018.
 */
class PasswordValidator : Validator {

    override fun validate(text: String?): ValidationResult {

        return Validations.validarSenha(text)
    }
}