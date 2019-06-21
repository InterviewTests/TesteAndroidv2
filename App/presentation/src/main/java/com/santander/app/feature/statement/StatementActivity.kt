package com.santander.app.feature.statement

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.santander.app.R
import com.santander.app.core.ui.base.BaseActivity
import com.santander.app.core.util.extension.hide
import com.santander.app.core.util.extension.show
import com.santander.app.core.util.extension.toast
import com.santander.domain.entity.business.Statement
import com.santander.domain.entity.business.UserAccount
import kotlinx.android.synthetic.main.activity_statement.*
import kotlinx.android.synthetic.main.content_statement.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class StatementActivity : BaseActivity(), StatementContract.View, StatementsAdapter.OnItemClickListener {

    private val presenter by inject<StatementContract.Presenter> { parametersOf(this) }

    private val statementsAdapter = StatementsAdapter(listener = this)

    override fun getLayoutRes(): Int = R.layout.activity_statement

    override fun initView(savedInstanceState: Bundle?) {
        rvStatement?.apply {
            adapter = statementsAdapter
            layoutManager = LinearLayoutManager(this@StatementActivity)
            itemAnimator = DefaultItemAnimator()
        }

//        swipeRefreshStatement?.apply {
//            setOnRefreshListener {
//                isRefreshing = true
//                presenter.fetchStatements()
//            }
//           setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
//        }

        setSupportActionBar(toolbar)


        appBar?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            container?.alpha = 1 - Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())
        })
        presenter.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_statement, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                displayLogout {
                    presenter.logout()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun displayUserAccount(userAccount: UserAccount) {
        toolbar?.title = userAccount.name
        tvBankAccount?.text = userAccount.bankAccount.agencyAndAccountFormatted()
        tvBalance?.text = userAccount.balance.formatted()
    }

    override fun displayStatements(statements: List<Statement>) {
        statementsAdapter.addAll(statements)
    }

    override fun onStatementClicked(item: Statement) {
        toast(item.desc)
    }

    override fun showLoading() {
        progressStatement.show()
    }

    override fun hideLoading() {
        progressStatement.hide()
    }

    override fun onBackPressed() {
        displayLogout {
            presenter.logout()
        }
    }

}
