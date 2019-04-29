package br.com.alex.bankappchallenge.feature.statement

class StatementReducer : StatementReducerContract {

    override fun reducer(
        currentState: StatementState?,
        nextState: StatementStates
    ) =
        currentState?.let {
            when (nextState) {
                is StatementStates.LoadingStatement -> it.copy(
                    isLoadingStatement = true,
                    isLoadError = false,
                    errorMessage = ""
                )
                is StatementStates.Error -> it.copy(
                    isLoadError = true,
                    isLoadingStatement = false,
                    statementList = listOf(),
                    errorMessage = nextState.errorMessage
                )
                is StatementStates.StatementListState -> it.copy(
                    isLoadingStatement = false,
                    isLoadError = false,
                    statementList = nextState.statementList,
                    errorMessage = ""
                )
            }
        } ?: StatementState()
}

interface StatementReducerContract {
    fun reducer(
        currentState: StatementState?,
        nextState: StatementStates
    ): StatementState
}