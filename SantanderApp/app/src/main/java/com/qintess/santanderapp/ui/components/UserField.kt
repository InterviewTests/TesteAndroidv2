package com.qintess.santanderapp.ui.components

import android.content.Context
import com.qintess.santanderapp.helper.Validator

class UserField(ctx: Context) : ValidatorEditText(ctx) {
    override var rule = { value: String -> Validator.isEmailValid(value) || Validator.isCpfValid(value) }
}