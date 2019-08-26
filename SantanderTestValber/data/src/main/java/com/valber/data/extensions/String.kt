package com.valber.data.extensions

import java.util.regex.Pattern

fun String.Companion.empty() = ""

fun String.isLowCase() : Boolean{
    val pattern = Pattern.compile(".*[a-z].*")
    return pattern.matcher(this).matches()
}
fun String.isAplhanumeric() : Boolean{
    val pattern = Pattern.compile(".*\\d.*")
    return pattern.matcher(this).matches()
}

fun String.isCharcacterSpecial() : Boolean{
    val pattern = Pattern.compile(".*[`~!@#\$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*")
    return pattern.matcher(this).matches()
}