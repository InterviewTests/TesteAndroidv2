package com.accenture.santander.dashBoard

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.accenture.santander.R
import com.accenture.santander.entity.Error
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.entity.ListStatement
import com.accenture.santander.entity.Statement
import com.accenture.santander.utils.DateTime
import com.accenture.santander.viewmodel.Account
import java.io.IOException
import javax.inject.Inject

class DashBoardPresenter(
    private val activity: Activity,
    private val view: View,
    private val iDashBoardPresenterOutput: DashBoardContracts.DashBoardPresenterOutput
) : DashBoardContracts.DashBoardPresenterInput, DashBoardContracts.DashBoardInteractorOutput {

    @Inject
    lateinit var iDashBoardInteractorInput: DashBoardContracts.DashBoardInteractorInput

    @Inject
    lateinit var loginRouter: DashBoardRouter

    init {
        DaggerDashBoardComponents
            .builder()
            .dashBoardModulo(DashBoardModulo(context = activity, view = view, dashBoardPresenter = this))
            .build()
            .inject(this)
    }

    override fun soliciteData() {
        iDashBoardInteractorInput.searchData()
    }

    override fun searchLogout(activity: Activity) {
        try {
            val ims = activity.getAssets()?.open(activity.getString(R.string.assets_logout))
            val drawable = Drawable.createFromStream(ims, null)
            iDashBoardPresenterOutput.loadLogout(drawable)
            ims?.close()
        } catch (ex: IOException) {
            iDashBoardPresenterOutput.failImageLogout()
        }
    }

    override fun logout() {
        iDashBoardPresenterOutput.mensageLogout()
        iDashBoardInteractorInput.deletaAccount()
        iDashBoardPresenterOutput.cleanData()
        loginRouter.popBackStack()
    }

    override fun resultData(user: UserEntity?) {
        user?.let {
            iDashBoardPresenterOutput.apresentationData(
                MutableLiveData<Account>().apply {
                    value = Account().mapper(it)
                }
            )
        }
    }

    override fun loadStatements() {
        iDashBoardPresenterOutput.visibleRefrash()
        iDashBoardInteractorInput.searchStatements()
    }

    override fun resultStatements(listStatement: ListStatement?) {
        if (listStatement?.error?.code != 0) {
            iDashBoardPresenterOutput.errorService(listStatement?.error?.message)
            return
        }

        val statementsMap = listStatement.statementList.map {
            com.accenture.santander.viewmodel.Statement().mapper(it ?: Statement())
        }.sortedByDescending {
            DateTime.conversor(it.date)
        }.toMutableList()

        iDashBoardPresenterOutput.apresentationStatements(
            MutableLiveData<MutableList<com.accenture.santander.viewmodel.Statement>>().apply {
                value = statementsMap
            })
        iDashBoardPresenterOutput.goneRefrash()
    }

    override fun failResquest(code: Int) {
        iDashBoardPresenterOutput.goneRefrash()
        iDashBoardPresenterOutput.failRequest()
    }

    override fun errorStatements(throwable: Throwable) {
        iDashBoardPresenterOutput.goneRefrash()
        iDashBoardPresenterOutput.errorStatements()
    }

    override fun failNetWork() {
        iDashBoardPresenterOutput.goneRefrash()
        iDashBoardPresenterOutput.failNetWork()
    }
}