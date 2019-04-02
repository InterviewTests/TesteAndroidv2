package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.base.mvp.BasePresenter
import br.com.rms.bankapp.data.repository.StatementRepository
import br.com.rms.bankapp.data.repository.user.UserRepository

class HomePresenter(
    private val userRepository: UserRepository,
    private val statementRepository: StatementRepository
) : BasePresenter<HomeContract.View>(), HomeContract.Presenter{

    private var nextPage: Int = 0
    private var maxPage = Int.MAX_VALUE
    private var loading = false

    override fun loadMoreStatemens() {
        if(nextPage < maxPage && !loading){
            loading = true
        }
    }

    override fun loadStatements() {
    }
}