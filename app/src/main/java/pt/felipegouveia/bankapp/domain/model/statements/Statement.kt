package pt.felipegouveia.bankapp.domain.model.statements

import java.util.*

data class Statement(
    val title: String? = null,
    val desc: String? = null,
    val date: Date? = null,
    val value: Double = 0.0
)