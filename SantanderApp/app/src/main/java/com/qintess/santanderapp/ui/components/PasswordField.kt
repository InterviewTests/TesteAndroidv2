package com.qintess.santanderapp.ui.components

import android.content.Context
import com.qintess.santanderapp.helper.Validator

class PasswordField(ctx: Context) : ValidatorEditText(ctx) {
    override var rule = {value: String -> Validator.isPasswordValid(value) }
}