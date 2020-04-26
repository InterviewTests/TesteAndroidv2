package br.com.raphael.everis.model

import java.util.*


data class Statement(
    val title: String,
    val desc: String,
    val date: Date,
    val value: Int
)