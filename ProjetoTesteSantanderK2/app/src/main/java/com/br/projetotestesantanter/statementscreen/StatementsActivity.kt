package com.br.projetotestesantanter.statementscreen

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.br.projetotestesantanter.R
import com.br.projetotestesantanter.api.model.LoginResponse
import com.br.projetotestesantanter.api.model.Statement
import com.br.projetotestesantanter.api.model.StatementListResponse
import com.br.projetotestesantanter.refactor.statementScreen.ViewHeaderStatements
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity() , StatementContract.View, ViewHeaderStatements.OnItemClickListener{
    override fun onItemClick(item: ImageView?) {
        finish()
    }


    override fun dataHeader(loginResponse: LoginResponse) {

        header_statements.bind(loginResponse)
    }

    override fun listStatement(statements: StatementListResponse) {

        recycler_statements.layoutManager = LinearLayoutManager(this)

        adapter = StatementAdapter(
            statements.statementListResponse as ArrayList<Statement>,
            this
        )

        recycler_statements.adapter = adapter

        hiddenProgressBar()

    }

    override fun showProgressBar() {

        progressBar.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        progressBar.visibility = View.GONE

    }


    lateinit var presenter: StatementContract.Presenter

    lateinit var loginResponse: LoginResponse

    lateinit var adapter: StatementAdapter

    override fun getContext(): Context {
        return this
    }

    override fun showErroMsg(msg: String) {

        Snackbar.make(findViewById(android.R.id.content),msg, Snackbar.LENGTH_LONG).show()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        if(intent.getParcelableExtra<LoginResponse>("login") != null){

            loginResponse = intent.getParcelableExtra("login")
            init()

        }

        setOnClickHeader()

    }

    private fun init() {
        presenter = StatementPresenter()
        presenter.attachView(this)
        presenter.resultLogin(loginResponse)
        presenter.start()

    }

    private fun setOnClickHeader() {
        header_statements.setListener(this)
    }

    override fun onDestroy() {
        // 4
        presenter.detachView()
        super.onDestroy()
    }
}