package br.com.cauejannini.testesantander.commons.form.validators

import br.com.cauejannini.testesantander.commons.Utils

/**
 * Created by cauejannini on 14/03/2018.
 */
class ValorMonetarioValidator : Validator {

    var min: Double? = null
    var max: Double? = null

    constructor() {}

    constructor(min: Double?, max: Double?) {
        this.min = min
        this.max = max
    }

    override fun validate(text: String?): ValidationResult {

        val validationResult = Validations.validarValorMonetario(text!!)
        if (validationResult.isValid) {
            if (min != null || max != null) {

                if (min != null && max != null && min!! > max!!) {
                    return ValidationResult(false, "Valor inválido")
                }

                val valorDouble = Utils.valorMonetarioParaDouble(text)

                if (valorDouble != null) {
                    if (min != null && valorDouble < min!!) {
                        return ValidationResult(false, "O valor mínimo é de " + Utils.doisDecimais(min))
                    }

                    return if (max != null && valorDouble > max!!) {
                        ValidationResult(false, "O valor máximo é de " + Utils.doisDecimais(max))
                    } else ValidationResult(true, "Válido")
                }
            }
        }
        return validationResult
    }
}