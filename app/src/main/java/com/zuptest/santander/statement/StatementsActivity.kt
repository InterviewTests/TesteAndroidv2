package com.zuptest.santander.statement

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.zuptest.santander.R
import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Statement
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayAccountInfo(agency: String, account: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayBalance(balance: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayStatements(statements: List<Statement>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}