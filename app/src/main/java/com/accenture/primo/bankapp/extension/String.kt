package com.accenture.primo.bankapp.extension

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun String.isEmail(): Boolean {
    val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val pattern: Pattern = Pattern.compile(EMAIL_PATTERN);

    return pattern.matcher(this).matches();
}

fun String.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.hasOneUpperCase(): Boolean {
    return Pattern.compile("[A-Z]").matcher(this).find()
}

fun String.hasOneNumber(): Boolean {
    return Pattern.compile("[0-9]").matcher(this).find()
}

fun String.hasOneSpecialCaracter(): Boolean {
    return Pattern.compile("\\W").matcher(this).find()
}

fun String.formatDate(): String {
    var spf = SimpleDateFormat("yyyy-MM-dd")
    val newDate: Date = spf.parse(this)
    spf = SimpleDateFormat("dd/MM/yyyy")

    return spf.format(newDate)
}

fun String.isCPF(): Boolean {
    if (this.isEmpty())
        return false

    val arrNumbers = arrayListOf<Int>()
    this.replace(".","").replace("-","").forEach {
        if (it.isDigit())
            arrNumbers.add(it.toString().toInt())
        else
            return false
    }

    if (arrNumbers.size != 11)
        return false

    //Checar numeros repetidos
    (0..9).forEach { n ->
        val arrDigits = arrayListOf<Int>()

        (0..10).forEach {
            arrDigits.add(n)
        }

        if (arrNumbers == arrDigits)
            return false
    }

    val intDV1: Int = ((0..8).sumBy {
        (it + 1) * arrNumbers[it]
    }).rem(11).let {
        if (it >= 10)0 else it
    }

    val intDV2: Int = ((0..8).sumBy {
        it * arrNumbers[it]
    }.let {
        (it + (intDV1 * 9)).rem(11)
    }).let {
        if (it >= 10)0 else it
    }

    return (arrNumbers[9] == intDV1 && arrNumbers[10] == intDV2)
}