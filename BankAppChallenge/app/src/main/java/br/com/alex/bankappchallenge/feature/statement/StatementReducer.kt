package br.com.alex.bankappchallenge.feature.statement

class StatementReducer : StatementReducerContract {

    override fun reducer(
        currentState: StatementState?,
        nextState: StatementStates
    ) =
        currentState?.let {
            when (nextState) {
                is StatementStates.Loading -> it.copy(
                    isLoading = true,
                    isLoadError = false
                )
                is StatementStates.Error -> it.copy(
                    isLoadError = true,
                    isLoading = false,
                    statementList = listOf()
                )
                is StatementStates.StatementListState -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    statementList = nextState.statementList
                )
                is StatementStates.UserAccountState -> it.copy(
                    userAccount = nextState.userAccount
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