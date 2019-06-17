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
    private val iDashBoardInteractorOutput: DashBoardContracts.DashBoardInteractorOutput
) : DashBoardContracts.DashBoardInteractorInput {


    @Inject
    lateinit var iConnect: IConnect

    @Inject
    lateinit var iUserRepository: IUserRepository

    @Inject
    lateinit var iStoragManager: IStoragManager

    @Inject
    lateinit var iServiceStatement: IServiceStatement

    init {
        DaggerDashBoardComponents
            .builder()
            .dashBoardModulo(DashBoardModulo(context = activity,dashBoardInteractor = this))
            .build()
            .inject(this)
    }

    override fun searchData() {
        iDashBoardInteractorOutput.resultData(iUserRepository.findDesc())
    }

    override fun searchStatements() {
        if (iConnect.verifyConnection()) {
            iUserRepository.findDesc()?.let {
                iServiceStatement.statement(it.id,
                    success = {
                        assert(it.code() == 200)

                        if (it.code() == 200) {
                            iDashBoardInteractorOutput.resultStatements(it.body())
                        } else {
                            iDashBoardInteractorOutput.failResquest(it.code())
                        }
                    },
                    failure = {
                        iDashBoardInteractorOutput.errorStatements(it)
                    })
            }
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

}