package dev.vitorpacheco.solutis.bankapp.statementsScreen

interface StatementsInteractorInput {
    fun fetchStatementsData(request: StatementsRequest)
}

class StatementsInteractor : StatementsInteractorInput {

    var output: StatementsPresenterInput? = null
    var worker = StatementsWorker()

    override fun fetchStatementsData(request: StatementsRequest) {
        request.userId?.let {
            worker.listStatements(request) { output?.presentStatementsData(it) }
        }
    }

    companion object {
        var TAG = StatementsInteractor::class.java.simpleName
    }

}