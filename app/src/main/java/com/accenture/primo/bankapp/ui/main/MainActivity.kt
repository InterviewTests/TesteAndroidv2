package com.accenture.primo.bankapp.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.accenture.primo.bankapp.R
import com.accenture.primo.bankapp.extension.isNull
import com.accenture.primo.bankapp.extension.toMoney
import com.accenture.primo.bankapp.model.Statement
import com.accenture.primo.bankapp.model.User
import com.accenture.primo.bankapp.ui.main.adapter.StatementItemAdapter
import com.accenture.primo.bankapp.ui.main.contracts.IMainActivity
import com.accenture.primo.bankapp.ui.main.contracts.IMainInteractor
import com.accenture.primo.bankapp.ui.main.contracts.IMainRouter
import com.accenture.primo.bankapp.EXTRA_KEY_LOGIN
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainActivity {
    internal var interactor: IMainInteractor? = null
    internal var router: IMainRouter? = null
    private val user by lazy { this.intent.extras?.getSerializable(EXTRA_KEY_LOGIN) as User }
    private lateinit var userStatements: MutableList<Statement>
    private lateinit var adapter: StatementItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainConfigurator.INSTANCE.configure(this)

        configToolbar()
        configRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        interactor?.fetchData(user)
    }

    private fun configRecyclerView() {
        userStatements =  mutableListOf()
        adapter = StatementItemAdapter(this)
        rvwStatements.layoutManager = LinearLayoutManager(this)
        rvwStatements.adapter = adapter
    }

    private fun configToolbar() {
        tblToolbar.inflateMenu(R.menu.main_menu)
        tblToolbar.setOnMenuItemClickListener {
            router?.showLoginScreen()
            return@setOnMenuItemClickListener true
        }
    }

    override fun showLoading() {
        pgbLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pgbLoading.visibility = View.GONE
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    fun getUserStatements(): List<Statement> = this.userStatements

    override fun onSuccess(mainviewmodel: MainModel.MainViewModel) {
        if(mainviewmodel.user.isNull())
            return

        tblToolbar.title = mainviewmodel.user.name
        txtBankAccount.text = "${mainviewmodel.user.agency} / ${mainviewmodel.user.bankAccount}"
        txtBalance.text = "${mainviewmodel.user.balance.toMoney()}"

        this.userStatements.clear()
        this.userStatements.addAll(mainviewmodel.statements)
        adapter.notifyDataSetChanged()
    }
}
