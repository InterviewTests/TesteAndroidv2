package com.accenture.santander.statements

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.accenture.santander.R
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.utils.DateTime
import com.accenture.santander.viewmodel.Account
import java.io.IOException
import javax.inject.Inject

class StatementPresenter(
    private val activity: Activity,
    private val view: View,
    private val iStatementPresenterOutput: StatementContracts.StatementPresenterOutput
) : StatementContracts.StatementPresenterInput, StatementContracts.StatementInteractorOutput {

    @Inject
    lateinit var iStatementInteractorInput: StatementContracts.StatementInteractorInput

    @Inject
    lateinit var loginRouter: StatementRouter

    init {
        DaggerStatementComponents
            .builder()
            .statementModule(StatementModule(context = activity, view = view, statementPresenter = this))
            .build()
            .inject(this)
    }

    override fun soliciteData() {
        iStatementInteractorInput.searchData()
    }

    override fun searchLogout(activity: Activity) {
        try {
            val ims = activity.getAssets()?.open(activity.getString(R.string.assets_logout))
            val drawable = Drawable.createFromStream(ims, null)
            iStatementPresenterOutput.loadLogout(drawable)
            ims?.close()
        } catch (ex: IOException) {
            iStatementPresenterOutput.failImageLogout()
        }
    }

    override fun logout() {
        iStatementPresenterOutput.mensageLogout()
        iStatementInteractorInput.deletaAccount()
        iStatementPresenterOutput.cleanData()
        loginRouter.popBackStack()
    }

    override fun resultData(user: UserEntity?) {
        user?.let {
            iStatementPresenterOutput.apresentationData(
                MutableLiveData<Account>().apply {
                    value = Account().mapper(it)
                }
            )
        }
    }

    override fun loadStatements() {
        iStatementPresenterOutput.visibleRefrash()
        iStatementInteractorInput.searchIdUserStatements()
    }

    override fun resultStatements(listStatement: ListStatement?) {

        if (listStatement?.error?.code != 0) {
            iStatementPresenterOutput.errorService(listStatement?.error?.message)
            return
        }

        val statementsMap = listStatement.statementList.map {
            com.accenture.santander.viewmodel.Statement().mapper(it)
        }.sortedByDescending {
            DateTime.conversor(it.date)
        }.toMutableList()

        iStatementPresenterOutput.apresentationStatements(
            MutableLiveData<MutableList<com.accenture.santander.viewmodel.Statement>>().apply {
                value = statementsMap
            })
        iStatementPresenterOutput.goneRefrash()
    }

    override fun failResquest(code: Int) {
        iStatementPresenterOutput.goneRefrash()
        iStatementPresenterOutput.failRequest()
    }

    override fun errorStatements(throwable: Throwable) {
        iStatementPresenterOutput.goneRefrash()
        iStatementPresenterOutput.errorStatements()
    }

    override fun failNetWork() {
        iStatementPresenterOutput.goneRefrash()
        iStatementPresenterOutput.failNetWork()
    }
}