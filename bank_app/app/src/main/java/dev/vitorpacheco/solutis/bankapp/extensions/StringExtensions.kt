package dev.vitorpacheco.solutis.bankapp.extensions

import com.google.common.base.Strings
import dev.vitorpacheco.solutis.bankapp.utils.ValidacaoUtils
import java.util.regex.Pattern

fun String?.isValidEmail(): Boolean {
    if (this == null || Strings.isNullOrEmpty(this)) return false

    val regExpn = ("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b")

    val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)

    return matcher.matches()
}

fun String?.isValidCpf() = ValidacaoUtils.isValidCpf(this)


fun String?.isValidPassword(): Boolean {
    if (this == null || Strings.isNullOrEmpty(this)) return false

    val regExpn = ("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{3,}\$")

    val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)

    return matcher.matches()
}

