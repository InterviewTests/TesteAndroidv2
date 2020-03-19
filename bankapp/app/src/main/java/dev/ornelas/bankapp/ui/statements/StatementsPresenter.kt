package dev.ornelas.bankapp.ui.statements

import dev.ornelas.banckapp.domain.interactors.GetStatements
import dev.ornelas.bankapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatementsPresenter(
    private val view: StatementsContract.View,
    private val getStatements: GetStatements
) : StatementsContract.Presenter {


    override fun onLoadStatments(userId: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = getStatements(userId)
            withContext(Dispatchers.Main) {

                if (result.isFailure) {
                    view.displayStatements(StatementsViewModel(errorMessage = R.string.statements_failed))
                } else {
                    view.displayStatements(StatementsViewModel(result.data?.map { it.toStatementViewModel() }))
                }

            }
        }
    }

    override fun onLogout() {
        TODO("Not yet implemented")
    }
}