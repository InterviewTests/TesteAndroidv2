package projects.kevin.bankapp.user.detail

import java.math.BigDecimal

data class BankStatements(
    val value: BigDecimal,
    val title: String,
    val desc: String,
    val date: String
)

data class DetailApiResponse(
    val statementList: ArrayList<BankStatements>? = null
)
