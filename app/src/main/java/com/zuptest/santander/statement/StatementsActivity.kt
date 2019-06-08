package com.zuptest.santander.statement

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuptest.santander.R
import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Statement
import kotlinx.android.synthetic.main.activity_statements.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class StatementsActivity : Activity(), StatementsContract.View {

    private val presenter by inject<StatementsContract.Presenter> { parametersOf(this) }

    companion object {
        private const val ACCOUNT_EXTRA = "extra_account"

        fun newIntent(context: Context, account: Account) =
            Intent(context, StatementsActivity::class.java).apply {
                putExtra(ACCOUNT_EXTRA, account)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        presenter.init(intent.getSerializableExtra(ACCOUNT_EXTRA) as Account)
    }

    override fun displayHolderName(name: String) {
        holderName?.text = name
    }

    override fun displayAccountInfo(agency: String, account: String) {
        accountValue?.text = getString(R.string.header_account, account, agency)
    }

    override fun displayBalance(balance: String) {
        balanceValue?.text = balance
    }

    override fun displayStatements(statements: List<Statement>) {
        statementsRecyclerView?.apply {
            adapter = StatementAdapter(statements)
            layoutManager = LinearLayoutManager(this@StatementsActivity)
            addItemDecoration(DividerItemDecoration(this@StatementsActivity, LinearLayoutManager.VERTICAL)
                .apply {
                    setDrawable(resources.getDrawable(R.drawable.divider_list))
                })
        }
    }
}