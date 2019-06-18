package com.accenture.santander.statements

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.viewmodel.Account

class StatementContracts {

    interface StatementPresenterInput {
        fun soliciteData()
        fun searchLogout(activity: Activity)
        fun logout()
        fun loadStatements()
    }

    interface StatementInteractorInput {
        fun searchData()
        fun searchStatements(iduser: Int)
        fun deletaAccount()
        fun searchIdUserStatements()
    }

    interface StatementInteractorOutput {
        fun resultData(user: UserEntity?)
        fun resultStatements(listStatement: ListStatement?)
        fun errorStatements(throwable: Throwable)
        fun failNetWork()
        fun failResquest(code: Int)
    }

    interface StatementPresenterOutput {
        fun apresentationData(user: LiveData<Account>)
        fun cleanData()
        fun loadLogout(drawable: Drawable)
        fun apresentationStatements(statements: LiveData<MutableList<com.accenture.santander.viewmodel.Statement>>)
        fun goneRefrash()
        fun visibleRefrash()

        fun mensageLogout()
        fun failImageLogout()
        fun errorStatements()
        fun failNetWork()
        fun failRequest()
        fun errorService(mensage: String?)
    }
}