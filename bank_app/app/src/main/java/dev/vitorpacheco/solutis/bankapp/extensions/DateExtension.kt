package dev.vitorpacheco.solutis.bankapp.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date?.format(): String? {
    if (this == null) return null

    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

    return formatter.format(this)
}
