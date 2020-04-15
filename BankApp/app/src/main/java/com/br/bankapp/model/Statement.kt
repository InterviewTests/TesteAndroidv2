package com.br.bankapp.model

import java.io.Serializable

data class Statement(
    val title: String,
    val desc: String,
    val date: String,
    val valueFormatted: String,
    val value: Double
) : Serializable