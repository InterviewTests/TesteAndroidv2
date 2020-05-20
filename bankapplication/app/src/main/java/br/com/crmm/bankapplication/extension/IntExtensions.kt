package br.com.crmm.bankapplication.extension

fun Int?.nonNullable(): Int{
    return this?: 0
}