package com.ygorcesar.testeandroidv2.home.model

import java.math.BigDecimal

data class Statement(
    var title: String,
    var desc: String,
    var date: String,
    var value: BigDecimal
)