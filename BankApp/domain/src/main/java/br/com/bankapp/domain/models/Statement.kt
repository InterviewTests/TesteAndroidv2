package br.com.bankapp.domain.models

import java.util.*

data class Statement(
    val id: Long,
    val title: String,
    val desc: String,
    val date: Date,
    val value: Double,
    val userId: Int
)