package br.com.rphmelo.bankapp.common.extensions

fun Double.formatMoney(digits: Int) = String.format("R$%.${digits}f", this)