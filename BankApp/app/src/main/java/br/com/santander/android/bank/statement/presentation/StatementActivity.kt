package br.com.santander.android.bank.statement.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.santander.android.bank.R
import br.com.santander.android.bank.core.extensions.formatAgency
import br.com.santander.android.bank.core.extensions.toCurrency
import br.com.santander.android.bank.login.domain.model.Account
import br.com.santander.android.bank.login.presentation.LoginActivity
import br.com.santander.android.bank.statement.di.StatementInjection
import br.com.santander.android.bank.statement.domain.model.Statement
import br.com.santander.android.bank.statement.domain.model.Statements
import kotlinx.android.synthetic.main.activity_statements.*

internal class StatementActivity : AppCompatActivity(), StatementContract.View {

    private lateinit var account: Account
    private var presenter = StatementPresenter(this, StatementInjection.interactor)
    private var statements: Statements? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        if (savedInstanceState != null) {
            account = getAccount(savedInstanceState)
            statements = getStatements(savedInstanceState)
        } else {
            account = getAccount(intent.extras!!)
        }

        setupViews()
        presenter.onCreate()
        presenter.requestStatements(account.userId.toInt())
    }

    private fun setupViews() {
        setSupportActionBar(statements_toolbar)
        val formattedAccount = "${account.bankAccount} / ${account.agency.formatAgency()}"
        txt_name.text = account.name
        txt_account.text = formattedAccount
        txt_balance.text = account.balance.toCurrency()
        btn_exit.setOnClickListener { logout() }
        rv_statements.layoutManager = LinearLayoutManager(this)
        swip_refresh.setOnRefreshListener {
            presenter.requestStatements(account.userId.toInt())
        }
    }

    private fun logout() {
        startActivity(LoginActivity.init(this))
        finish()
    }

    override fun showLoading() {
        swip_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swip_refresh.isRefreshing = false
    }

    override fun showStatements(statements: Statements) {
        this.statements = statements
        rv_statements.adapter = StatementAdapter(statements.list)
    }

    override fun showTryAgainMessage() {
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(ACCOUNT_PARAM_KEY, account)
        outState?.putSerializable(STATEMENTS_PARAM_KEY, statements)
    }

    companion object {
        private const val ACCOUNT_PARAM_KEY = "account_param"
        private const val STATEMENTS_PARAM_KEY = "statements_param"

        fun init(context: Context, account: Account): Intent {
            val intent = Intent(context, StatementActivity::class.java)
            intent.putExtra(ACCOUNT_PARAM_KEY, account)
            return intent
        }

        internal fun getAccount(bundle: Bundle): Account {
            return bundle.getSerializable(ACCOUNT_PARAM_KEY) as Account
        }

        internal fun getStatements(bundle: Bundle): Statements {
            return bundle.getSerializable(STATEMENTS_PARAM_KEY) as Statements
        }
    }
}