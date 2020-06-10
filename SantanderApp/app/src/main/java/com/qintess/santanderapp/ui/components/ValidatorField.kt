package com.qintess.santanderapp.ui.components

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText

abstract class ValidatorEditText(ctx: Context): AppCompatEditText(ctx) {
    // Propriedade que define a regra de validação, onde o parâmetro value é o texto atual do campo, e o retorno da lambda é um booleano que diz se o valor passado é válido
    protected open var rule: (value: String) -> Boolean = { true }

    // Computed val que retorna se campo é válido
    val valid: Boolean
        get() = this.rule(this.text.toString())
}