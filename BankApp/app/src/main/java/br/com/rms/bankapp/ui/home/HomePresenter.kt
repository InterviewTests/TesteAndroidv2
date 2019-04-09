package br.com.rms.bankapp.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.mvp.BasePresenter
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.data.remote.model.StatementResponse
import br.com.rms.bankapp.data.repository.StatementRepository
import br.com.rms.bankapp.data.repository.user.UserRepository
import br.com.rms.bankapp.utils.Mask
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat

class HomePresenter(
    private val userRepository: UserRepository,
    private val statementRepository: StatementRepository
) : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var nextPage: Int = 0
    private var maxPage = Int.MAX_VALUE
    private var loading = false


    override fun loadMoreStatemens() {
        if (nextPage < maxPage && !loading) {
            loading = true
        }
        statementRepository.loadRemoteStatement(nextPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<StatementResponse> {
                override fun onSuccess(statementResponse: StatementResponse) {
                    val statementList = statementResponse.statementList
                    if (!statementList.isNullOrEmpty()) {
                        view?.onMoreStatementsReady(statementList)
                        updatePageData(statementList)
                    }
                    view?.hideLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    view?.showLoading()
                }

                override fun onError(e: Throwable) {
                    view?.showErrorMessage(R.string.error_message_load_statement)
                    view?.hideLoading()

                }

            })

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun loadStatements() {
        statementRepository.loadRemoteStatement(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<StatementResponse> {
                override fun onSuccess(statementResponse: StatementResponse) {
                    val statementList = statementResponse.statementList
                    if (!statementList.isNullOrEmpty()) {
                        view?.onMoreStatementsReady(statementList)
                        updatePageData(statementList)
                    }
                    view?.hideLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    view?.showLoading()
                }

                override fun onError(e: Throwable) {
                    view?.hideLoading()
                    view?.showErrorMessage(R.string.error_message_load_statement)

                }

            })
    }

    private fun updatePageData(statements: List<Statement?>) {
        loading = false
        nextPage++
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun loadUserAccount() {
        userRepository.getLocalUserAccount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Account> {
                override fun onSuccess(t: Account) {
                    updateUserName(t.name!!)
                    updateUserAccount(t.agency!!, t.bankAccount!!)
                    updateUserBalance(t.balance)
                    view?.hideLoading()

                }

                override fun onSubscribe(d: Disposable) {
                    view?.showLoading()
                }

                override fun onError(e: Throwable) {
                    view?.showErrorMessage(R.string.error_message_load_user_account_data)
                    view?.hideLoading()


                }

            })

    }

    fun updateUserName(name: String) {
        view?.updateUserName(name)
    }

    fun updateUserAccount(agency: String, account: String) {
        var agencyFormat = Mask().addMask(agency, "##.######-#")
        view?.updateUserAccount(account, agencyFormat)
    }

    fun updateUserBalance(balance: Double?) {
        val nf = NumberFormat.getCurrencyInstance()
        view?.updateUserBalance(nf.format(balance))
    }
}