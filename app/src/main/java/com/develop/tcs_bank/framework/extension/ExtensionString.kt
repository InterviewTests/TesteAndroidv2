package com.develop.tcs_bank.framework.extension

fun String.removeMask(): String {
    val v = ""
    return this.replace("[.]".toRegex(), v).replace("[-]".toRegex(), v).replace("[/]".toRegex(), v).replace("[(]".toRegex(), v).replace("[ ]".toRegex(), v).replace("[:]".toRegex(), v).replace("[)]".toRegex(), v)
}

fun String.formatMask(mask: String): String {
    if (this.isNullOrEmpty()) {
        return ""
    }
    return formatString(mask, this)
}

fun formatString(mask: String, s: String): String {
    val str = s.removeMask()
    var masked = ""
    var i = 0
    for (m in mask.toCharArray()) {
        if (m != '#') {
            masked += m
            continue
        }
        try {
            masked += str[i]
        } catch (e: Exception) {
            break
        }
        i++
    }
    return masked
}

