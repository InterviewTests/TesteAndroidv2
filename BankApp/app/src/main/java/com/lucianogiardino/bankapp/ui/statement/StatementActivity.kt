package com.lucianogiardino.bankapp.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucianogiardino.bankapp.R
import com.lucianogiardino.bankapp.domain.model.Statement
import com.lucianogiardino.bankapp.domain.model.User
import kotlinx.android.synthetic.main.activity_statement.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class StatementActivity : AppCompatActivity(),
    StatementContract.View {

    private val statementPresenter: StatementContract.Presenter by inject { parametersOf(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)
        setupUserData()
        statementPresenter.fetchStatement()
    }

    override fun setupUserData(){
        var accountText: String = User.agency.toString() + " / " + User.bankAccount.toString()
        var format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt","BR"))
        var balanceFormated = format.format(User.balance)
        tvName.text = User.name
        tvAccount.text = accountText
        tvBalance.text = balanceFormated
    }

    override fun setupStatementList(statementList: ArrayList<Statement>) {
        var recyclerView:RecyclerView = rvStatement
        recyclerView.adapter = StatementAdapter(statementList, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }
}
