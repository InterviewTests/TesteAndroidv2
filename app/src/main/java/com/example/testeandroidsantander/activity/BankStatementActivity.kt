package com.example.testeandroidsantander.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.testeandroidsantander.R
import com.example.testeandroidsantander.controller.StatementController
import com.example.testeandroidsantander.controller.StatementListener
import com.example.testeandroidsantander.model.Statement
import com.example.testeandroidsantander.model.StatementList
import com.example.testeandroidsantander.recyclerview.StatementAdapter
import kotlinx.android.synthetic.main.activity_bank_statement.*

class BankStatementActivity : AppCompatActivity(), StatementListener {

    private val statementController = StatementController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_statement)
        init()
    }

    private fun init() {
        statementController.getStatement()
        bankImageView.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView(statement: StatementList) {
        val recyclerView = detailRecyclerView
        recyclerView.adapter = StatementAdapter(statement, this)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    override fun onStatementAvailable(statement: StatementList) {
        setupRecyclerView(statement)
    }
}
