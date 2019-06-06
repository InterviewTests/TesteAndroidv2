package business.model

data class BankInfo(
    val account: String,
    val agency: String,
    val balance: Money
)