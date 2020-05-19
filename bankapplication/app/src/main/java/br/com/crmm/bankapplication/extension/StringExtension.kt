package br.com.crmm.bankapplication.extension

import br.com.crmm.bankapplication.util.ValidationUtil
import org.koin.core.KoinComponent
import org.koin.core.inject

private val validationUtil = object : KoinComponent{
    val validationUtil: ValidationUtil by inject()
}.validationUtil

fun String.isNotValidEmail() = validationUtil.isValidEmail(this).not()

fun String.isNotValidPassword() = validationUtil.isValidPassword(this).not()

fun String?.nonNullable(): String{
    return this?: ""
}

fun String.applyBankAccountMask(): String {
    return if(length == 9) {
        "${substring(0, 2)}.${substring(2, 8)}-${substring(8, 9)}"
    } else this
}