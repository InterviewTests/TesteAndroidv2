package br.com.alex.bankappchallenge.feature.statement

class UserAccountReducer : UserAccountReducerContract {

    override fun reducer(
        currentState: UserAccountState?,
        nextState: UserAccountStates
    ) =
        currentState?.let {
            when (nextState) {
                is UserAccountStates.LoadingUserAccount -> it.copy(
                    isLoadingUserAccount = true
                )
                is UserAccountStates.UserAccountData -> it.copy(
                    isLoadingUserAccount = false,
                    userAccount = nextState.userAccount
                )
            }
        } ?: UserAccountState()
}

interface UserAccountReducerContract {
    fun reducer(
        currentState: UserAccountState?,
        nextState: UserAccountStates
    ): UserAccountState
}