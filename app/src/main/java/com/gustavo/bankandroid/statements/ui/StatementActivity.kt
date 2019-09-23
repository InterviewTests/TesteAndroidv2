package com.gustavo.bankandroid.statements.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gustavo.bankandroid.R
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.extensions.verticalLinearLayout
import com.gustavo.bankandroid.statements.data.mapper.StatementMapper
import com.gustavo.bankandroid.statements.injection.StatementInjection
import com.gustavo.bankandroid.statements.repository.DataRepository
import com.gustavo.bankandroid.statements.repository.DataRepositoryMock
import com.gustavo.bankandroid.statements.ui.adapter.StatementAdapter
import com.gustavo.bankandroid.statements.usecase.FetchStatementUseCaseImpl
import com.gustavo.bankandroid.statements.usecase.GetLoggedUserInfoUseCaseMock
import com.gustavo.bankandroid.statements.usecase.StatementUseCases
import com.gustavo.bankandroid.statements.viewmodel.StatementViewModel
import com.gustavo.bankandroid.statements.viewmodel.StatementViewModelFactory
import kotlinx.android.synthetic.main.activity_statement.*

class StatementActivity : AppCompatActivity(), StatementInjection {

    private lateinit var viewModel: StatementViewModel
    private var adapter = StatementAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)
        viewModel = getViewModel()
        setObservers()
        viewModel.fetchData()
    }

    private fun setObservers() {
        viewModel.userInfo.observe(this, Observer {
            setUserInfo(it)
        })
        viewModel.statementListLiveData.observe(this, Observer {
            setStatementList(it)
        })
    }

    private fun setStatementList(list: List<UserStatementItem>) {
        recyclerView.verticalLinearLayout(this)
        recyclerView.adapter = adapter
        adapter.statementList = list
    }

    private fun setUserInfo(userInfo: UserInfo) {
        nameTextView.text = userInfo.name
        accountTextView.text = userInfo.account
        valueTextView.text = userInfo.balance.toString()
    }

    private fun getViewModel() =
        ViewModelProviders.of(
            this,
            StatementViewModelFactory(this)
        ).get(StatementViewModel::class.java)

    override val fetchStatementUseCase: StatementUseCases.FetchStatementUseCase by lazy {
        FetchStatementUseCaseImpl(dataRepository, StatementMapper())
    }
    override val getLoggedUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase by lazy {
        GetLoggedUserInfoUseCaseMock()
    }
    override val dataRepository: DataRepository by lazy {
        DataRepositoryMock()
    }
}
