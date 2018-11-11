package br.com.santander.android.bank.statement.presentation

import br.com.santander.android.bank.core.extensions.request
import br.com.santander.android.bank.statement.domain.interactor.StatementInteractor
import io.reactivex.disposables.CompositeDisposable

internal class StatementPresenter(private val view: StatementContract.View,
                                  private val statementInteractor: StatementInteractor)
    : StatementContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun onCreate() {}

    override fun requestStatements(userId: Int) {
        view.showLoading()
        disposable.request(
            statementInteractor.getStatements(userId),
            { view.showStatements(it) },
            { view.showTryAgainMessage() },
            { view.hideLoading() }
        )
    }

    override fun onDestroy() {
        if(!disposable.isDisposed) disposable.dispose()
    }
}