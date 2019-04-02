package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.base.mvp.BasePresenter
import br.com.rms.bankapp.data.repository.StatementRepository
import br.com.rms.bankapp.data.repository.user.UserRepository

class HomePresenter(
    private val userRepository: UserRepository,
    private val statementRepository: StatementRepository
) : BasePresenter<HomeContract.View>(), HomeContract.Presenter{

}