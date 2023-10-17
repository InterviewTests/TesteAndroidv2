package fingerfire.com.extractbank.features.statements.ui.viewstate

import fingerfire.com.extractbank.features.statements.data.StatementsResponse

sealed class StatementViewState {
    data class Success(val data: List<StatementsResponse>) : StatementViewState()
    data class Error(val errorMessage: String? = null) : StatementViewState()
}
