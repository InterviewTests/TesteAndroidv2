package br.com.rms.bankapp.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.mvp.BasePresenter
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.remote.model.StatementResponse
import br.com.rms.bankapp.data.repository.statement.StatementRepositoryContract
import br.com.rms.bankapp.data.repository.user.UserRepositoryContract
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val homeView: HomeContract.View,
    private val userRepository: UserRepositoryContract,
    private val statementRepository: StatementRepositoryContract
) : BasePresenter<HomeContract.View>(homeView), HomeContract.Presenter {

    private var nextPage: Int = 0
    private var maxPage = Int.MAX_VALUE
    private var loading = false


    override fun loadMoreStatements() {
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
                        homeView.onMoreStatementsReady(statementList)
                        updatePageData()
                    }
                    homeView.hideLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    homeView.showLoading()
                }

                override fun onError(e: Throwable) {
                    homeView.showErrorMessage(R.string.error_message_load_statement)
                    homeView.hideLoading()

                }

            })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun loadStatements() {
        statementRepository.loadRemoteStatement(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<StatementResponse> {
                override fun onSuccess(statementResponse: StatementResponse) {
                    val statementList = statementResponse.statementList
                    if (!statementList.isNullOrEmpty()) {
                        homeView.onMoreStatementsReady(statementList)
                        updatePageData()
                    }
                    homeView.hideLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    homeView.showLoading()
                }

                override fun onError(e: Throwable) {
                    homeView.hideLoading()
                    homeView.showErrorMessage(R.string.error_message_load_statement)

                }
            })
    }

    private fun updatePageData() {
        loading = false
        nextPage++
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun loadUserAccount() {
        userRepository.getLocalUserAccountId().observe(homeView, Observer<Int> { accountId ->
            accountId?.let {
                userRepository.getLocalUserAccount(it)
                    .observe(homeView, Observer<Account> { account ->
                        homeView.accountIsReady(account)
                    })
            }

        })
    }

}