package business.model

import java.util.*

data class Statement(
    val title: String,
    val description: String,
    val date: Date,
    val amount: Money
)