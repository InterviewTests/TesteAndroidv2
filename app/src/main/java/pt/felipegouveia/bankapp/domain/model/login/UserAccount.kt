package pt.felipegouveia.bankapp.domain.model.login

import pt.felipegouveia.bankapp.util.extension.toReal

data class UserAccount(
    val userId: Int? = -1,
    val name: String? = null,
    val bankAccount: String? = null,
    val agency: String? = null,
    val balance: Double? = 0.0
) {
    fun balanceToReal(): String{
        return balance?.toReal()?: "null"
    }

    fun formatAgency(): String? {
       return if(agency != null){
           StringBuilder(agency)
               .insert(8, "-")
               .insert(2, ".")
               .toString()
       } else {
           agency
       }
    }
}