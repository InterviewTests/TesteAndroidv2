package dev.vitorpacheco.solutis.bankapp.statementsScreen

import dev.vitorpacheco.solutis.bankapp.api.BankService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface StatementsInteractorInput {
    fun fetchStatementsData(request: StatementsRequest)
}

class StatementsInteractor : StatementsInteractorInput {

    var output: StatementsPresenterInput? = null
    private var statementsWorkerInput: StatementsWorkerInput? = null

    override fun fetchStatementsData(request: StatementsRequest) {
        statementsWorkerInput = StatementsWorker()

        request.userId?.let {
            BankService.create().listStatements(request.userId)
                .enqueue(object : Callback<StatementsResponse> {
                    override fun onResponse(
                        call: Call<StatementsResponse>,
                        response: Response<StatementsResponse>
                    ) {
                        response.body()?.let {
                            output?.presentStatementsData(it)
                        }
                    }

                    override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {
                        output?.presentStatementsData(
                            StatementsResponse(
                                statementList = null,
                                error = StatementError(
                                    message = t.message
                                )
                            )
                        )
                    }
                })
        }

        val response = StatementsResponse()

    }

    companion object {
        var TAG = StatementsInteractor::class.java.simpleName
    }

}