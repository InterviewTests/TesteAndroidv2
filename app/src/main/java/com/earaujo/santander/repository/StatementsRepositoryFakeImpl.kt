package com.earaujo.santander.repository

import com.earaujo.santander.repository.models.StatementsListModel
import com.earaujo.santander.repository.models.StatementsResponse

class StatementsRepositoryFakeImpl : StatementsRepository {

    override fun fetchStatements(statementsRepositoryCallback: StatementsRepositoryCallback) {
        statementsRepositoryCallback.onData(
            Resource.success(
                StatementsResponse(
                    listOf(
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70),
                        StatementsListModel("Pagamento", "Conta de luz", "12/12/2018", 82.70)
                    ),
                    null
                )
            )
        )
    }
}