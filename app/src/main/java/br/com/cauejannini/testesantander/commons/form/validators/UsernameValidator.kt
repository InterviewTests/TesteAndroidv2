package br.com.cauejannini.testesantander.commons.form.validators

import br.com.cauejannini.testesantander.commons.Utils

/**
 * Created by cauejannini on 14/03/2018.
 */
class UsernameValidator : Validator {

    override fun validate(text: String?): ValidationResult {

        return Validations.validarUsuario(text)
    }
}