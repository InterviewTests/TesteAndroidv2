package com.example.otavioaugusto.testesantander.statements

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.otavioaugusto.testesantander.R
import com.example.otavioaugusto.testesantander.adapters.StatementsAdapter
import com.example.otavioaugusto.testesantander.login.LoginContrato
import com.example.otavioaugusto.testesantander.model.StatementListItem
import com.example.otavioaugusto.testesantander.model.Statements
import com.example.otavioaugusto.testesantander.model.UserAccount
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity(), StatementsContrato.View {


    lateinit var presenter: StatementsContrato.Presenter
     //lateinit var recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        presenter = StatementsPresenter(this)

        initRecyler()

        var intent = intent
        val name = intent.getStringExtra("name")
        val bankAccount = intent.getStringExtra("bankAccount")
        val agency = intent.getStringExtra("agency")
        val balance = intent.getDoubleExtra("balance",0.0)
        val id = intent.getIntExtra("userId",1)


        txtNome.setText(name)
        txtConta.setText(bankAccount+ "/" +agency)
        txtSaldo.setText("R$ "+balance.toString())

        if (id!=null)
            presenter.obterListadaAPI(id.toString())
    }


    override fun listaStatements(lista: List<StatementListItem>) {

        val adapter = StatementsAdapter(lista,this)
        recyclerView.adapter = adapter

    }

    override fun mensagensErro(msg: String) {
        Log.e("Erro", "${msg}")
    }

    override fun mensagemOk(msg: String) {
        Log.e("OK", "${msg}")
    }

    fun initRecyler(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)




    }

}


