package pt.felipegouveia.bankapp.data.login.model

data class UserAccount(
    val userId: Int? = 0,
    val name: String? = null,
    val bankAccount: String? = null,
    val agency: String? = null,
    val balance: Double? = 0.0
)