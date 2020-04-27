package br.com.raphael.everis.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}