package com.example.bankapp.view.logged

import com.example.bankapp.model.Statement
import com.example.bankapp.model.User

interface LoggedContract {
    interface View{
        fun updateStatements(statments: List<Statement>)
        fun askLogout()
        fun exit()
    }

    interface Presenter {
        fun getStatementList(user: User)
        fun logOut()
    }
}