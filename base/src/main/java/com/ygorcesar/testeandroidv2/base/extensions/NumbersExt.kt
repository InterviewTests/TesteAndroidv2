package com.ygorcesar.testeandroidv2.base.extensions

import java.math.BigDecimal
import java.text.NumberFormat

fun BigDecimal.formatCurrency() = NumberFormat.getCurrencyInstance(localeBrazil()).format(this)!!

fun Double.formatCurrency() = NumberFormat.getCurrencyInstance(localeBrazil()).format(this)!!