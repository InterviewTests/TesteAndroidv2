package com.farage.testeandroidv2.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.farage.testeandroidv2.R
import com.farage.testeandroidv2.di.KodeinContainers
import com.farage.testeandroidv2.domain.model.StatementsModel
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.ui.HomeAdapter.HomeAdapter
import com.farage.testeandroidv2.util.Constants
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.farage.testeandroidv2.viewmodel.HomeViewModel
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.newInstance
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    private lateinit var userData: Bundle
    private lateinit var user: UserAccount
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViewModel()
        observeViewModel()
        userData = intent.extras!!
        user = userData.getSerializable(Constants.USER_KEY_BUNDLE) as UserAccount
        fillLabels(user)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        viewModel.loadStatements(user.userId!!.toInt())
    }

    private fun initViewModel() {
        viewModel = KodeinContainers.diBaseProject.newInstance { HomeViewModel(instance(), instance()) }
    }

    private fun observeViewModel() {
        viewModel.let {
            it.getScreenState.observe(this, Observer {
                onResultReceived(it)
            })
            it.getRouterState.observe(this, Observer { changeScreen(it) })
        }
    }

    private fun changeScreen(intent: Intent?) {
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.menu_exit) {
            viewModel.logout(this)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun fillLabels(user: UserAccount) {
        user.let {
            main_name_client.text = it.name
            main_bankAccount.text = "${it.agency} / ${it.bankAccount}"
            main_balance.text = it.balance
        }
    }

    private fun initRecyclerView(statements: List<StatementsModel>) {
        main_rv_statements.layoutManager = LinearLayoutManager(this)
        main_rv_statements.adapter = HomeAdapter(statements)
    }

    private fun onResultReceived(state: ResultState<List<StatementsModel>>?) {
        when (state?.resultType) {
            ResultType.SUCCESS -> initRecyclerView(state.data!!)
            ResultType.ERROR -> errorOnLoadStatements(state.message)
            ResultType.EMPTY_DATA -> emptyStatements()
        }
    }

    private fun emptyStatements() {
        Toast.makeText(this, "Dados vazios", Toast.LENGTH_SHORT).show()
    }

    private fun errorOnLoadStatements(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}