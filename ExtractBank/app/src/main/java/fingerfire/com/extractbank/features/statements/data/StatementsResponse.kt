package fingerfire.com.extractbank.features.statements.data

data class StatementsResponse(
    val idUser: String,
    val date: String,
    val value: Double,
    val type: String,
    val typeTransaction: String
)