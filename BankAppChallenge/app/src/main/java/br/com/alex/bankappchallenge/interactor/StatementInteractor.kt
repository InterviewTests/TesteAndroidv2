package br.com.alex.bankappchallenge.interactor

import br.com.alex.bankappchallenge.interactor.mapper.StatementMapperContract
import br.com.alex.bankappchallenge.interactor.mapper.UserMapperContract
import br.com.alex.bankappchallenge.model.FormatedStatement
import br.com.alex.bankappchallenge.model.FormatedUserAccount
import br.com.alex.bankappchallenge.network.model.UserAccount
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import br.com.alex.bankappchallenge.repository.StatementRepositoryContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StatementInteractor(
    private val statementRepositoryContract: StatementRepositoryContract,
    private val loginRepositoryContract: LoginRepositoryContract,
    private val statementMapperContract: StatementMapperContract,
    private val userMapperContract: UserMapperContract
) : StatementInteractorContract {

    private val disposables = CompositeDisposable()
    private lateinit var statementInteractorOutput: StatementInteractorOutput
    private val userAccount: UserAccount by lazy {
        loginRepositoryContract.getUserAccount()
    }

    override fun fetchStatements() {
        val subscribe = statementRepositoryContract
            .fetchStatements(userAccount.userId)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { statementInteractorOutput.loadingStatements() }
            .toObservable()
            .map {
                if (it.error.code != 0L) {
                    statementInteractorOutput.loadingStatementError(it.error.message)
                } else {
                    val mapper = statementMapperContract.mapper(it.statementList)
                    statementInteractorOutput.fetchStatementSuccess(mapper)
                }
            }
            .subscribe()

        disposables.add(subscribe)
    }

    override fun fetchUserAccount() {
        statementInteractorOutput.loadingUserAccount()
        val mapper = userMapperContract.mapper(userAccount)
        statementInteractorOutput.userAccountData(mapper)
    }

    override fun statementInteractorOutputImpl(statementInteractorOutput: StatementInteractorOutput) {
        this.statementInteractorOutput = statementInteractorOutput
    }

    override fun logout() {
        loginRepositoryContract.logout()
        statementInteractorOutput.logout()
    }

    override fun clear() {
        disposables.clear()
    }
}

interface StatementInteractorOutput {
    fun loadingStatements()
    fun loadingUserAccount()
    fun logout()
    fun userAccountData(formatedUserAccount: FormatedUserAccount)
    fun fetchStatementSuccess(formatedStatementList: List<FormatedStatement>)
    fun loadingStatementError(errorMessage: String)
}