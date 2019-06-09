package com.androiddeveloper.santanderTest.ui.statements

import com.androiddeveloper.santanderTest.data.model.statements.ItemDTO
import com.androiddeveloper.santanderTest.data.model.statements.Statements
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.DataDTO
import java.lang.ref.WeakReference

interface IStatementContract {

    interface StatementInteractor {
        fun fetchUserData()
        fun prepareData(data: Data)
        fun bind(activity: BankInfoActivity)
        fun fetchUserBalance(id: Int)
        fun deleteDb()
    }

    interface StatementPresenterInput {
        fun parseData(data: Data)
        fun prepareBalance(statements: Statements)
    }

    interface StatementInput {
        fun onUserDbError(message: String)
        fun showData(data: DataDTO)
        fun onBalanceResponseError()
        fun showBalanceList(balanceList: ArrayList<ItemDTO>)
        fun onDeleteDbSuccess()
    }
}