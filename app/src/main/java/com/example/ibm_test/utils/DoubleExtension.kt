package com.example.ibm_test.utils

fun Double.toMoney(): String {
    val value = this.toString()
    var aux = ""

    value.reversed().forEachIndexed{ index, char ->
        aux += if (index > 0 && index % 3 == 0)
            ".$char"
        else
            "$char"

    }

    if(!aux.contains(",00")) aux = aux.reversed().plus(",00")

    return aux
}