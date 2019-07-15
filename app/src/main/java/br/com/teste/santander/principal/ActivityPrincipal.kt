package br.com.teste.santander.principal

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import br.com.projetoaccenturebank.client.retrofit.OnCallback
import br.com.teste.santander.R
import br.com.teste.santander.model.Dados
import br.com.teste.santander.model.Login
import br.com.teste.santander.recycle.RecycleDados
import br.com.teste.santander.util.Alert
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.text.NumberFormat
import java.util.*


class ActivityPrincipal : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, OnCallback {
    var lista = ArrayList<Dados>()
    val mRecycle = RecycleDados(lista)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            inicializa()
        } catch (err : Exception) {
            err.printStackTrace()
        }
    }

    fun inicializa() {

        val recycle = findViewById<RecyclerView>(R.id.recycle)
        recycle.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        recycle.layoutManager = mLayoutManager
        recycle.adapter = mRecycle

        refresh.setOnRefreshListener(this)

        val login = Login().returnLogin(this)
        usuario.text = login!!.name
        conta.text = String.format("%s / %s", login.bankAccount, login.agency)

        val meuLocal = Locale("pt", "BR")
        val formatado = NumberFormat.getCurrencyInstance(meuLocal).format(login.balance)
        saldo.text = formatado

        lista.clear()
        Dados().getDados(this)?.let { lista.addAll(it) }

        if(lista.isNullOrEmpty()) {
            refresh.isRefreshing = true
            TaskCarregaDados(this).execute()
        } else {
            this.lista.clear()
            this.lista.addAll(lista)

            mRecycle.notifyDataSetChanged()
        }


    }


    override fun onRefresh() {
        TaskCarregaDados(this).execute()
    }

    override fun onBackPressed() {
        Login().logout(this, true)
    }

    override fun onRetorno(aBoolean: Boolean, mensagem: String) {
        if(aBoolean) {
            lista.clear()
            lista.addAll(Dados().getDados(this)!!)
            mRecycle.notifyDataSetChanged()
        }else
                Alert.show(this, resources.getString(R.string.erro), Html.fromHtml(mensagem).toString(), false)

        refresh.isRefreshing = false
    }
}
