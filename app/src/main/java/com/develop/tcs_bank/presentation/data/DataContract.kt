package com.develop.tcs_bank.presentation.data

import com.develop.tcs_bank.domain.models.StatementList

class DataContract {
    interface View {
        fun navigate()
        fun setText(nome: String, conta: Int, saldo: String, agency: String)
        fun configureList(statment: List<StatementList>)
        fun showMessage(msg: String)
    }
    interface Presenter {
        fun processLogout()
        fun setName(): String
        fun setConta(): Int
        fun setBalance(): String
        fun setAgency(): String
        fun loadList()
    }
}