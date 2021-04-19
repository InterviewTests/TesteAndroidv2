package com.example.appbanksantander_accenturetalentacquisition.Presenter.Main

import com.example.appbanksantander_accenturetalentacquisition.Model.StatementModel

interface MainContract {
    interface View{
        fun showStatement(statement: List<StatementModel>)
    }

    interface UserLoads{
        fun loadStatement(userId: Int)
    }
}