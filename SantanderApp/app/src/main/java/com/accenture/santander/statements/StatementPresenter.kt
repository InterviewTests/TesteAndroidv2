package com.accenture.santander.statements

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.accenture.santander.R
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.utils.DateTime
import com.accenture.santander.utils.StatusBar
import com.accenture.santander.viewmodel.Account
import com.accenture.santander.viewmodel.Statement
import java.io.IOException
import javax.inject.Inject

class StatementPresenter(
    activity: Activity,
    view: View,
    private val iStatementPresenterOutput: StatementContracts.StatementPresenterOutput
) : StatementContracts.StatementPresenterInput, StatementContracts.StatementInteractorOutput {

    @Inject
    lateinit var iStatementInteractorInput: StatementContracts.StatementInteractorInput

    @Inject
    lateinit var loginRouter: StatementRouter

    init {
        DaggerStatementComponents
            .builder()
            .statementModule(
                StatementModule(
                    context = activity,
                    view = view,
                    statementPresenter = this
                )
            )
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
        user?.let { iStatementPresenterOutput.apresentationData(Account().mapper(it)) }
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

        iStatementPresenterOutput.apresentationStatements(statementsMap)
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

    override fun cleanStatements() {
        iStatementPresenterOutput.getStatements().clear()
    }

    override fun cleanAndAddStatements(statements: MutableList<Statement>) {
        iStatementPresenterOutput.getStatements().clear()
        iStatementPresenterOutput.getStatements().addAll(statements)
    }

    override fun statusBarColor(activity: Activity) {
        StatusBar.setStatusBarColor(activity, ContextCompat.getColor(activity, R.color.colorPrimary))
    }

    override fun onDestroyStatusBarColor(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}