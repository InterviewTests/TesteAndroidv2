package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

data class UserAccountDTO(
    private val userId: String? = "",
    private val name: String? = "",
    private val bankAccount: String? = "",
    private val agency: String? = "",
    private val balance: Double? = 0.0
)