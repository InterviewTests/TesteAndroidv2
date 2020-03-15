package dev.vitorpacheco.solutis.bankapp.extensions

import java.text.DateFormat
import java.text.DateFormat.MEDIUM
import java.util.*

fun Date?.format(): String? {
    if (this == null) return null

    return DateFormat.getDateInstance(MEDIUM, Locale("pt", "BR")).format(this)
}
