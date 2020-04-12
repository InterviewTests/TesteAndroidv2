package pt.felipegouveia.bankapp.presentation.statements.entity

import java.util.*

data class StatementPresentation(
    val title: String? = null,
    val desc: String? = null,
    val date: Date? = null,
    val value: Double = 0.0
)