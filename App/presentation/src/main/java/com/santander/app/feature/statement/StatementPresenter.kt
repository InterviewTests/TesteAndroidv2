package com.santander.app.feature.statement

import com.santander.app.core.ui.base.AbstractPresenter
import com.santander.app.core.util.extension.defaultSchedulers
import com.santander.domain.usecase.ICleanAccountUseCase
import com.santander.domain.usecase.IFetchStatementUseCase
import com.santander.domain.usecase.IGetAccountUseCase
import io.reactivex.rxkotlin.subscribeBy

class StatementPresenter(override var view: StatementContract.View,
                         private val getAccountUseCase: IGetAccountUseCase,
                         private val cleanAccountUseCase: ICleanAccountUseCase,
                         private var statementUseCase: IFetchStatementUseCase
): AbstractPresenter<StatementContract.View>(), StatementContract.Presenter {

    private var userId: Int = -1

    override fun start() {
        launch {
            getAccountUseCase.execute()
                .defaultSchedulers()
                .doOnNext {
                    userId = it.userId
                    fetchStatements()
                }
                .subscribeBy(
                    onNext = {
                        view.displayUserAccount(it)
                    },
                    onError = {
                        view.handleError(it)
                    }
                )
        }
    }

    override fun fetchStatements() {
        if (userId == -1) return
        launch {
            statementUseCase.execute(params = userId)
                .defaultSchedulers()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribeBy(
                    onNext = {
                        view.displayStatements(it)
                    },
                    onError = {
                        view.handleError(it)
                    }
                )
        }
    }

    override fun logout() {
        launch {
            cleanAccountUseCase.execute()
                .defaultSchedulers()
                .subscribeBy(
                    onComplete = {
                        view.logout()
                    },
                    onError = {
                        view.handleError(it)
                    }
                )
        }
    }
}