package dev.vitorpacheco.solutis.bankapp.extensions

import java.text.DateFormat
import java.text.DateFormat.SHORT
import java.util.*

fun Date?.format(): String? {
    if (this == null) return null

    return DateFormat.getDateInstance(SHORT, Locale("pt", "BR")).format(this)
}
