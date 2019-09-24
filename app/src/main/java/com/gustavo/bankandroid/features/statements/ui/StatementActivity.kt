package com.gustavo.bankandroid.features.statements.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gustavo.bankandroid.R
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.extensions.toRealCurrency
import com.gustavo.bankandroid.extensions.verticalLinearLayout
import com.gustavo.bankandroid.features.statements.injection.StatementInjection
import com.gustavo.bankandroid.features.statements.injection.StatementModule
import com.gustavo.bankandroid.features.statements.injection.StatementModuleImpl
import com.gustavo.bankandroid.features.statements.ui.adapter.StatementAdapter
import com.gustavo.bankandroid.features.statements.viewmodel.StatementViewModel
import com.gustavo.bankandroid.features.statements.viewmodel.StatementViewModelFactory
import kotlinx.android.synthetic.main.activity_statement.*
import org.jetbrains.anko.sdk27.listeners.onClick

class StatementActivity : AppCompatActivity(), StatementInjection {

    override val statementModule: StatementModule by lazy {
        StatementModuleImpl(this)
    }

    private lateinit var viewModel: StatementViewModel
    private var adapter = StatementAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)
        viewModel = getViewModel()
        setObservers()
        setListeners()
        viewModel.fetchData()
    }

    private fun setListeners() {
        loggoutButton.onClick {
            viewModel.logout()
        }
    }

    private fun setObservers() {
        viewModel.userInfo.observe(this, Observer {
            setUserInfo(it)
        })
        viewModel.statementListLiveData.observe(this, Observer {
            setStatementList(it)
        })
        viewModel.userLoggedOutLiveData.observe(this, Observer {
            if(it)finish()
        })
        viewModel.showErrorLiveData.observe(this, Observer {
            showError()
        })
    }

    private fun showError() {
        val dialog = AlertDialog.Builder(this)
            .create()
        dialog.setTitle(getString(R.string.statement_dialog_error_title))
        dialog.setMessage(getString(R.string.statement_dialog_error_message))
        dialog.show()
    }

    private fun setStatementList(list: List<UserStatementItem>) {
        recyclerView.verticalLinearLayout(this)
        recyclerView.adapter = adapter
        adapter.statementList = list
    }

    private fun setUserInfo(userInfo: UserInfo) {
        nameTextView.text = userInfo.name
        accountTextView.text = getString(R.string.statement_screen_account, userInfo.bankAccount.toString(), userInfo.agency.toString())
        valueTextView.text = userInfo.balance.toRealCurrency()
    }

    private fun getViewModel() =
        ViewModelProviders.of(
            this,
            StatementViewModelFactory(this)
        ).get(StatementViewModel::class.java)

}
