package com.earaujo.santander.util

fun String.isValidUsername(): Boolean = LoginUtils.isValidUsername(this)
fun String.isValidPassword(): Boolean = LoginUtils.isValidPassword(this)
