package com.example.ibm_test.utils

fun String.hasOneUpperCase() : Boolean{
    return this.none { it.isUpperCase() }
}