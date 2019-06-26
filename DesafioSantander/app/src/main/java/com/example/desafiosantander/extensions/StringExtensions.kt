package com.example.desafiosantander.extensions

import android.util.Patterns
import br.com.concrete.canarinho.validator.Validador
import com.example.desafiosantander.utils.Constants.FORMAT_DATE_PT_BR
import com.example.desafiosantander.utils.Constants.FORMAT_DATE_SERVER
import com.example.desafiosantander.utils.Constants.REGEX_PASSWORD
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.regex.Pattern

fun String.isCpfOrEmail(): Boolean {
    val patternPassword = Patterns.EMAIL_ADDRESS
    val matcherPassword = patternPassword.matcher(this)

    return Validador.CPF.ehValido(this) || matcherPassword.matches()
}

fun String.isPassword() = Pattern.compile(REGEX_PASSWORD)
    .matcher(this).matches()

fun String.formatDate(): String {
    val sdf = SimpleDateFormat(FORMAT_DATE_SERVER, Locale("pt", "BR"))
    val date = sdf.parse(this)
    return SimpleDateFormat(FORMAT_DATE_PT_BR, Locale("pt", "BR")).format(date)
}