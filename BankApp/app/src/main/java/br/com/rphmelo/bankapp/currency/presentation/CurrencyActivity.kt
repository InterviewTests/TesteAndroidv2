package br.com.rphmelo.bankapp.currency.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import br.com.rphmelo.bankapp.R
import br.com.rphmelo.bankapp.common.extensions.formatAgency
import br.com.rphmelo.bankapp.common.extensions.formatMoney
import br.com.rphmelo.bankapp.common.utils.ErrorMessage
import br.com.rphmelo.bankapp.common.utils.GsonHelper
import br.com.rphmelo.bankapp.common.utils.Variables
import br.com.rphmelo.bankapp.currency.api.CurrencyService
import br.com.rphmelo.bankapp.currency.presentation.adapters.StatementListAdapter
import br.com.rphmelo.bankapp.currency.domain.models.CurrencyView
import br.com.rphmelo.bankapp.currency.domain.interactor.CurrencyInteractor
import br.com.rphmelo.bankapp.currency.domain.models.StatementResponse
import br.com.rphmelo.bankapp.currency.repository.CurrencyRepository
import br.com.rphmelo.bankapp.login.domain.models.UserAccount
import br.com.rphmelo.bankapp.login.presentation.LoginActivity
import kotlinx.android.synthetic.main.activity_currency.*
import kotlinx.android.synthetic.main.activity_currency.view.*

class CurrencyActivity : AppCompatActivity(), CurrencyView {

    private lateinit var toolbar: Toolbar
    private lateinit var presenter: CurrencyPresenter
    private lateinit var repository: CurrencyRepository
    private lateinit var preferences: SharedPreferences
    private lateinit var userAccount: UserAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        toolbar = findViewById(R.id.toolbar)
        preferences = getSharedPreferences(Variables.PREFERENCES_NAME, Context.MODE_PRIVATE)
        repository = CurrencyRepository(CurrencyService(), preferences)
        presenter = CurrencyPresenter(this, CurrencyInteractor(repository))

        getToolbarData()

        presenter.loadStatementList(userAccount.userId)
        startToolbarSetup()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun setToolbarData(account: UserAccount) {
        lblBalanceAmount?.text = account?.balance.formatMoney(2)
        toolbar.title = account.name
        lblAccountAgency.text = "${account.bankAccount} / ${account.agency.formatAgency()}"
    }

    override fun showProgress() {
        currency_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        currency_progress.visibility = View.GONE
    }

    override fun setStatementListData(statementResponse: StatementResponse) {
        val recyclerView = rvList
        recyclerView.adapter = StatementListAdapter(statementResponse.statementList, this)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    override fun setStatementListError() {
        showErrorMessage(getString(R.string.statement_list_error_message))
    }

    override fun setupToolbar() {
        toolbar.inflateMenu(R.menu.menu)
        toolbar.setOnMenuItemClickListener {
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
            finish()
            return@setOnMenuItemClickListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun startToolbarSetup() {
        presenter.setupToolbar()
    }

    private fun getToolbarData() {
        val loginResponse = intent.extras.getString(Variables.LOGIN_RESPONSE_KEY).toString()
        userAccount = GsonHelper().fromJsonLoginResponse(loginResponse).userAccount
        presenter.setToolbarData(userAccount)
    }

    private fun showErrorMessage(message: String) {
        ErrorMessage().showErrorMessage(this, message)
    }
}
