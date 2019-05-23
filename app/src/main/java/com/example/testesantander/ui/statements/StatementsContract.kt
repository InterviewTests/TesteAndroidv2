package com.example.testesantander.ui.statements

import com.example.testesantander.domain.model.StatementsData
import com.example.testesantander.mvp.Contract

interface StatementsContract{
    interface View: Contract.View{

        fun getList(statementList: Array<StatementsData>)
    }
    interface Presenter: Contract.Presenter<View>{

        fun getStatementsList()
    }
}