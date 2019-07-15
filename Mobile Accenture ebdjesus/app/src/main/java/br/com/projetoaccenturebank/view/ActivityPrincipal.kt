package br.com.projetoaccenturebank.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import br.com.projetoaccenturebank.RecycleStatment
import br.com.projetoaccenturebank.client.retrofit.OnCallback
import br.com.projetoaccenturebank.client.retrofit.task.TaskStatement
import br.com.projetoaccenturebank.login.R
import br.com.projetoaccenturebank.model.Login
import br.com.projetoaccenturebank.model.StatementList
import br.com.projetoaccenturebank.util.Alerta
import br.com.projetoaccenturebank.util.SendIntent
import kotlinx.android.synthetic.main.principal_activity.*
import kotlinx.android.synthetic.main.principal_activity.view.*
import java.lang.Exception
import java.text.NumberFormat
import java.util.*

class ActivityPrincipal : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,OnCallback {

    val lista = ArrayList<StatementList>()
    var mRecycle = RecycleStatment(lista)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal_activity)
        try {
            controles()
        } catch (err : Exception) {
            err.printStackTrace()
        }
    }

    fun controles() {

        val recycle = findViewById<RecyclerView>(R.id.recycle)
        recycle.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        recycle.layoutManager = mLayoutManager
        recycle.adapter = mRecycle

        refresh.setOnRefreshListener(this)

        val login = Login().retornar(this)
        usuario.text = login!!.name
        conta.text = String.format("%s / %s", login.bankAccount, login.agency)

        val meuLocal = Locale("pt", "BR")
        val formatado = NumberFormat.getCurrencyInstance(meuLocal).format(login.balance)
        saldo.text = formatado

        val lista = StatementList().retornar(this)
        if(lista.isNullOrEmpty()) {
            refresh.isRefreshing = true
            TaskStatement(this).execute()
        } else {
            this.lista.clear()
            this.lista.addAll(lista)

            mRecycle.notifyDataSetChanged()
        }


    }

    override fun onRefresh() {
        TaskStatement(this).execute()
    }

    override fun onRetorno(aBoolean: Boolean, mensagem: String) {
        if(aBoolean) {
            lista.clear()
            lista.addAll(StatementList().retornar(this)!!)
            mRecycle.notifyDataSetChanged()
        }else
            Alerta.show(this, "Erro", Html.fromHtml(mensagem).toString(), false)

        refresh.isRefreshing = false
    }

    override fun onBackPressed() {
        StatementList().limpar(this)
        Login().deslogar(this, true)
    }
}