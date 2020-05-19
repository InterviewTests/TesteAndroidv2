package br.com.bankapp.utils

import br.com.bankapp.data.api.network_responses.LoginResponse
import br.com.bankapp.data.api.network_responses.UserAccountResponse

object SampleData {
    val loginResponse = LoginResponse(
        UserAccountResponse(
            userId = 1,
            name = "User",
            bankAccount = "2050",
            agency = "012314564",
            balance = 3.3445
        )
    )
}