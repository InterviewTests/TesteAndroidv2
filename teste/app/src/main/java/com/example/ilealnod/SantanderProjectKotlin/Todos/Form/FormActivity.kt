package com.example.ilealnod.SantanderProjectKotlin.Todos.Form

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.example.ilealnod.SantanderProjectKotlin.R
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.AccountInfo
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.ClientData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class FormActivity : AppCompatActivity() {

// implementação da tela de formulario

    private var btn_logout    : ImageView    ? = null
    private var tv_nome       : TextView     ? = null
    private var tv_agencia    : TextView     ? = null
    private var tv_bankAccount: TextView     ? = null
    private var tv_saldo      : TextView     ? = null
    private var rv_recycler   : RecyclerView ? = null

    private var context       : Context? = null
    var clientData            : ClientData? = ClientData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)

        inicializarVariaveis()
        inicializarAcao()
        logout()
    }


    private fun logout() {
        btn_logout!!.setOnClickListener {
            finish()
        }
    }

    private fun inicializarAcao() {
        getClientData()
        clientPopulate(clientData!!)
        getStatemant()
    }

    private fun inicializarVariaveis() {
        context = applicationContext

        tv_nome = findViewById(R.id.tv_nome_cliente)
        tv_bankAccount = findViewById(R.id.tv_agencia)
        tv_agencia = findViewById(R.id.tv_contaNumero)
        tv_saldo = findViewById(R.id.tv_saldoNumero)
        rv_recycler = findViewById(R.id.recycler)
        btn_logout = findViewById(R.id.btn_logout)
    }
// Pega os dados do Cliente
    private fun getClientData() {
        val json = intent.getStringExtra("DATACLIENT")
        clientData = Gson().fromJson(json, ClientData::class.java)
    }

// Mapeamento dos dados do cliente da api no layout

    private fun clientPopulate(clientData: ClientData?) {

        clientData.let {

            tv_nome?.text = clientData?.name
            tv_agencia?.text = clientData?.bankAccount
            tv_saldo?.text = ("R$ " + clientData?.balance.toString())
            tv_bankAccount?.text = clientData?.agency

        }
    }
// Pega os dados da API da lista
    private fun getStatemant() {
        val json = intent.getStringExtra("LISTACLIENT")
        val collectionType = object : TypeToken<List<AccountInfo>>() {
        }.type
        val list = Gson().fromJson(json, collectionType) as List<AccountInfo>
        rv_recycler?.adapter = FormAdapter(list, this)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_recycler!!.layoutManager = layoutManager
    }

    fun formateDate(date: String): String {
        return date.replace("-", "/")
    }
}
