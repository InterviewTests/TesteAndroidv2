package com.accenture.primo.bankapp.extension

fun Any?.isNull() : Boolean{
    return this == null
}

fun Any?.isNotNull() : Boolean{
    return this != null
}