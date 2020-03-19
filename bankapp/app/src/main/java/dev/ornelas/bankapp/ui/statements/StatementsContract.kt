package dev.ornelas.bankapp.ui.statements

interface StatementsContract {

    interface View {
        fun displayStatements(statementsViewModel: StatementsViewModel)
    }

    interface Presenter {
        fun onViewCreated()
        fun onLogout()
    }

}