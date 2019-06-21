package com.accenture.santander.statements

import android.app.Activity
import com.accenture.santander.interector.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.interector.dataManager.storag.IStoragManager
import com.accenture.santander.interector.remote.service.statement.IServiceStatement
import com.accenture.santander.interector.remote.service.IConnect

import javax.inject.Inject

class StatementInteractor(
    private val activity: Activity,
    private val iStatementInteractorOutput: StatementContracts.StatementInteractorOutput,
    private val iServiceStatement: IServiceStatement
) : StatementContracts.StatementInteractorInput {


    @Inject
    lateinit var iConnect: IConnect

    @Inject
    lateinit var iUserRepository: IUserRepository

    @Inject
    lateinit var iStoragManager: IStoragManager

    init {
        DaggerStatementComponents
            .builder()
            .statementModule(StatementModule(context = activity))
            .build()
            .inject(this)
    }

    override fun searchData() {
        iStatementInteractorOutput.resultData(iUserRepository.findDesc())
    }

    override fun searchStatements(iduser: Int) {
        if (iConnect.verifyConnection()) {
            iServiceStatement.statement(iduser,
                success = {
                    if (it.code() == 200) {
                        iStatementInteractorOutput.resultStatements(it.body())
                    } else {
                        iStatementInteractorOutput.failResquest(it.code())
                    }
                },
                failure = {
                    iStatementInteractorOutput.errorStatements(it)
                })
        } else {
            iStatementInteractorOutput.failNetWork()
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