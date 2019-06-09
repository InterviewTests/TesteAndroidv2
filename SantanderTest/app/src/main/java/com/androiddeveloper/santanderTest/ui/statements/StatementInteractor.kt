package com.androiddeveloper.santanderTest.ui.statements

import android.util.Log
import com.androiddeveloper.santanderTest.data.api.statement.StatementService
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.UserRepository
import com.androiddeveloper.santanderTest.manager.EncryptManager
import com.androiddeveloper.santanderTest.shared.base.BaseInteractor
import java.lang.ref.WeakReference

class StatementInteractor : BaseInteractor(), IStatementContract.StatementInteractor {

    lateinit var bankInfo: WeakReference<IStatementContract.StatementInput>
    var statementPresenter = StatementPresenterInput()
    lateinit var userDao: UserRepository

    override fun bind(activity: BankInfoActivity) {
        this.bankInfo = WeakReference(activity)
        statementPresenter.bankInfo = WeakReference(activity)

        userDao = UserRepository(activity.applicationContext)
    }

    override fun fetchUserData() {
        val disposable = userDao.getUser()
            .subscribe({ res ->
                val decrypted = EncryptManager.decrypt(res.data)
                prepareData(decrypted!!)
            }) { err ->
                bankInfo.get()?.onUserDbError("Erro ao recuperar seus dados")
            }

        compositeDisposable?.add(disposable)
    }

    override fun prepareData(data: Data) {
        statementPresenter.parseData(data)
    }

    override fun fetchUserBalance(id: Int) {
        val disposable = StatementService.requestStatement(id)
            .subscribe({ res ->
                res.body()?.let { statementPresenter.prepareBalance(it) }
            }) { err ->
                Log.d("Error", err.message)
                bankInfo.get()?.onBalanceResponseError()
            }

        compositeDisposable?.add(disposable)
    }

    override fun deleteDb() {
        val disposable = userDao.deleteUser().subscribe()
        compositeDisposable?.add(disposable)
    }
}