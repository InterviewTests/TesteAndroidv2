package pt.felipegouveia.bankapp.data.statements.model

import java.util.*

data class Statement(
    val title: String? = null,
    val desc: String? = null,
    val date: Date? = null,
    val value: Double = 0.0
)