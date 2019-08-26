package com.valber.santandertestvalber.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.valber.data.model.Statement
import com.valber.data.model.UserAccount
import com.valber.data.model.showAccount
import com.valber.data.model.showBalance
import com.valber.santandertestvalber.R
import com.valber.santandertestvalber.databinding.ActivityStatementBinding
import com.valber.santandertestvalber.presentation.*
import com.valber.santandertestvalber.presentation.prensenter.StatementPresenter
import kotlinx.android.synthetic.main.activity_statement.*
import kotlinx.android.synthetic.main.layout_insed_toolbar.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class StatementActivity : AppCompatActivity() {

    private val statementeViewModel: StatementViewModel by currentScope.viewModel(this)
    private var statementActivity: ActivityStatementBinding? = null
    private val user by lazy { intent.extras.getParcelable<UserAccount>(StatementActivity::class.java.simpleName) }
    private val snackbar by lazy {
        Snackbar.make(container, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { statementeViewModel.get(user.userId) }
    }
    private var adapterStatement = StatementAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statementActivity = DataBindingUtil.setContentView(this, R.layout.activity_statement)

        name_user_text.text = user.name
        contar_user.text = user.showAccount()
        saldo_user.text = user.showBalance()

        recyclerview_statement.isNestedScrollingEnabled = true
        recyclerview_statement.adapter = adapterStatement

        statementeViewModel.get(user.userId)
        statementeViewModel.statement.observe(this, Observer {
            updateUi(it)
        })
        logout()
    }

    private fun updateUi(resource: Resource<List<Statement>>) {
        resource?.let { it ->
            when (it.state) {
                ResourceState.LOADING -> print("carregango..")
                ResourceState.SUCCESS -> it.data?.let { it1 -> printRestult(it1) }
                ResourceState.ERROR -> print(it.message)
            }
            it.data?.let {
                adapterStatement.submitList(it)
            }
            it.message?.let { snackbar.show() }
        }
    }

    private fun logout() {
        statementActivity?.presenter = object :
            StatementPresenter {
            override fun onClick() {
                startActivity(Intent(this@StatementActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun printRestult(result: List<Statement>) {
        println("\n\n------> ${result.size}")
    }
}
