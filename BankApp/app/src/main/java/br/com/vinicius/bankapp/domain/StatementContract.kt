package br.com.vinicius.bankapp.domain

import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.internal.BaseCallback

class StatementContract {

    interface IStatement{
        val date: String
        val desc: String
        val title: String
        val value: Double
    }

    interface IRepository {
        fun startStatements(idUser:Int, onResult: BaseCallback<List<StatementModel>>)
    }
}