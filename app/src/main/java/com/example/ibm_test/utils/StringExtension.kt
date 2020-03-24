package com.example.ibm_test.utils

fun String.hasOneUpperCase() : Boolean{
    return this.none { it.isUpperCase() }
}

fun String.toHandlerAgency(): String {
    var aux = ""

    this.forEachIndexed{ index, char ->
        aux += if (index == 2)
            ".$char"
        else{
            if(index == this.length - 1)
                "-$char"
            else
                char
        }
    }

    return aux
}