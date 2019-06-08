package com.zuptest.santander.statement

import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.usecase.ListStatementsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class StatementsPresenter(
    private val view: StatementsContract.View,
    private val listStatementsUseCase: ListStatementsUseCase
) : StatementsContract.Presenter {

    override fun init(account: Account) {
        view.displayHolderName(account.holder)
        view.displayBalance(account.balance.formattedPrint())
        view.displayAccountInfo(
            account = account.bankInfo.account,
            agency = account.bankInfo.agency
        )

        listStatementsUseCase.execute(account.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    view.displayStatements(it)
                },
                onError = {},
                onComplete = {}
            )
    }
}