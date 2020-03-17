package dev.vitorpacheco.solutis.bankapp.statementsScreen

import dev.vitorpacheco.solutis.bankapp.api.BankApi
import dev.vitorpacheco.solutis.bankapp.api.BankService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface StatementsWorkerInput {
    fun listStatements(request: StatementsRequest, listener: (StatementsResponse) -> Unit)
}

class StatementsWorker : StatementsWorkerInput {

    var service: BankApi = BankService.createService()

    override fun listStatements(
        request: StatementsRequest,
        listener: (StatementsResponse) -> Unit
    ) {
        service.listStatements(request.userId)
            .enqueue(object : Callback<StatementsResponse> {
                override fun onResponse(
                    call: Call<StatementsResponse>,
                    response: Response<StatementsResponse>
                ) {
                    response.body()?.let {
                        listener(it)
                    }
                }

                override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {
                    listener(
                        StatementsResponse(
                            statementList = null,
                            error = StatementError(message = t.message)
                        )
                    )
                }
            })
    }

}