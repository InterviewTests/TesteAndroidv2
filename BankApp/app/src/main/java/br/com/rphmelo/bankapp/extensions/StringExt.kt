package br.com.rphmelo.bankapp.extensions

fun String.isEmail(): Boolean {
    val pattern = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()

    return matches(pattern)
}

fun String.isValidPassword(): Boolean {
    val pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W+).*\$".toRegex()
    return matches(pattern)
}