package br.com.bankapp.utils

import br.com.bankapp.data.api.network_responses.LoginResponse
import br.com.bankapp.data.api.network_responses.StatementListResponse
import br.com.bankapp.data.api.network_responses.StatementResponse
import br.com.bankapp.data.api.network_responses.UserAccountResponse
import br.com.bankapp.data.utils.convertStringToDate

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

    val statementResponse = StatementListResponse(
        listOf(
            StatementResponse(
                title = "Pagamento",
                description = "Conta de luz",
                date = "2018-08-15",
                value = -50.0
                ),
            StatementResponse(
                title = "TED Recebida",
                description = "Joao Alfredo",
                date = "2018-07-25",
                value = -745.03
                )
        )
    )
}