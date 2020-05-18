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