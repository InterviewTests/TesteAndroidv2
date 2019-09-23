package com.gustavo.bankandroid.features.statements.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.gustavo.bankandroid.R
import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.contants.Constants
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.extensions.verticalLinearLayout
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.gustavo.bankandroid.features.login.repository.UserRepositoryImpl
import com.gustavo.bankandroid.features.statements.injection.StatementInjection
import com.gustavo.bankandroid.features.statements.repository.DataRepository
import com.gustavo.bankandroid.features.statements.repository.DataRepositoryImpl
import com.gustavo.bankandroid.features.statements.ui.adapter.StatementAdapter
import com.gustavo.bankandroid.features.statements.usecase.FetchStatementUseCaseImpl
import com.gustavo.bankandroid.features.statements.usecase.GetLoggedUserInfoUseCaseImpl
import com.gustavo.bankandroid.features.statements.usecase.StatementUseCases
import com.gustavo.bankandroid.features.statements.viewmodel.StatementViewModel
import com.gustavo.bankandroid.features.statements.viewmodel.StatementViewModelFactory
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

    override val serverIterator = ServerIterator()

    override val fetchStatementUseCase: StatementUseCases.FetchStatementUseCase by lazy {
        FetchStatementUseCaseImpl(dataRepository)
    }
    override val getLoggedUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase by lazy {
        GetLoggedUserInfoUseCaseImpl(userRepository)
    }
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(database, serverIterator)
    }
    override val database: UserDatabase by lazy {
        Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            Constants.USER_DATABASE
        ).build()
    }
    override val dataRepository: DataRepository by lazy {
        DataRepositoryImpl(serverIterator)
    }
}
