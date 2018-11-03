package com.ygorcesar.testeandroidv2.base.common.utils

object RegexUtils {

    val CHARACTER_SPECIAL = "[~_}{\\[\\]\$&+,:;=?@#|/'<>.^*()%!-]".toRegex()

    val CHARACTER_CAPITALIZED = "[A-Z]".toRegex()

    val CHARACTERS = "[a-zA-Z]".toRegex()

    val NUMBER = "[0-9]".toRegex()
}