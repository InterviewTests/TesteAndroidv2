package com.accenture.santander.dashBoard

import android.app.Activity
import com.accenture.santander.interector.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.interector.dataManager.storag.IStoragManager
import com.accenture.santander.interector.remote.service.statement.IServiceStatement
import com.accenture.santander.interector.remote.service.Connect
import com.accenture.santander.interector.remote.service.IConnect

import javax.inject.Inject

class DashBoardInteractor(
    private val activity: Activity,
    private val iDashBoardInteractorOutput: DashBoardContracts.DashBoardInteractorOutput,
    private val iServiceStatement: IServiceStatement
) : DashBoardContracts.DashBoardInteractorInput {


    @Inject
    lateinit var iConnect: IConnect

    @Inject
    lateinit var iUserRepository: IUserRepository

    @Inject
    lateinit var iStoragManager: IStoragManager

    init {
        DaggerDashBoardComponents
            .builder()
            .dashBoardModulo(DashBoardModulo(context = activity, dashBoardInteractor = this))
            .build()
            .inject(this)
    }

    override fun searchData() {
        iDashBoardInteractorOutput.resultData(iUserRepository.findDesc())
    }

    override fun searchStatements(iduser: Int) {
        if (iConnect.verifyConnection()) {
            iServiceStatement.statement(iduser,
                success = {
                    if (it.code() == 200) {
                        iDashBoardInteractorOutput.resultStatements(it.body())
                    } else {
                        iDashBoardInteractorOutput.failResquest(it.code())
                    }
                },
                failure = {
                    iDashBoardInteractorOutput.errorStatements(it)
                })
        } else {
            iDashBoardInteractorOutput.failNetWork()
        }
    }

    override fun deletaAccount() {
        val user = iUserRepository.findDesc()
        user?.let {
            iUserRepository.delete(user)
        }
    }

    override fun searchIdUserStatements() {
        val data = iUserRepository.findDesc()
        searchStatements(data?.iduser ?: 0)
    }
}