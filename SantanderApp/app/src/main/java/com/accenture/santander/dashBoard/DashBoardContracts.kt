package com.accenture.santander.dashBoard

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.viewmodel.Account

class DashBoardContracts {

    interface DashBoardPresenterInput {
        fun soliciteData()
        fun searchLogout(activity: Activity)
        fun logout()
        fun loadStatements()
    }

    interface DashBoardInteractorInput {
        fun searchData()
        fun searchStatements()
        fun deletaAccount()
    }

    interface DashBoardInteractorOutput {
        fun resultData(user: UserEntity?)
        fun resultStatements(listStatement: ListStatement?)
        fun errorStatements(throwable: Throwable)
        fun failNetWork()
        fun failResquest(code: Int)
    }

    interface DashBoardPresenterOutput {
        fun apresentationData(user: LiveData<Account>)
        fun cleanData()
        fun loadLogout(drawable: Drawable)
        fun apresentationStatements(statements: LiveData<MutableList<com.accenture.santander.viewmodel.Statement>>)
        fun goneRefrash()
        fun visibleRefrash()

        fun mensageLogout()
        fun errorStatements()
        fun failNetWork()
        fun failRequest()
        fun errorService(mensage: String?)
    }
}