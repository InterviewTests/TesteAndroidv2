package com.qintess.santanderapp.model

enum class StatementType(val value: String) {
    Debit("Pagamento"),
    Credit("Recebimento")
}

data class StatementModel(val title: String,
                          val desc: String,
                          val date: String,
                          val value: Double,
                          val type: StatementType)