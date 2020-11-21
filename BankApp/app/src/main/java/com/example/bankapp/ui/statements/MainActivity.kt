package com.example.bankapp.ui.statements

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankapp.R
import com.example.bankapp.ui.BaseActivity
import com.example.bankapp.ui.adapters.StatementsAdapter
import com.example.bankapp.ui.login.LoginActivity
import com.example.bankapp.util.Constants
import com.example.bankapp.util.Converters
import com.example.bankapp.util.Mask
import com.example.bankapp.util.SessionManager
import com.example.domain.entities.Statement
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val sessionManager: SessionManager = get()
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (sessionManager.retornarUsuario()!!.id != Constants.Parametros.CODIGO_ID_VAZIO) {
            true -> {
                setContentView(R.layout.activity_main)
                setUserInformation()
                setupObservers()
                setupListeners()
            }
            else -> {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
        }
    }

    private fun setUserInformation() {
        mainViewModel.contaUsuario.value = sessionManager.retornarUsuario()
    }

    private fun setupObservers() {
        mainViewModel.listaStatements.observe(this, Observer {
            it?.let {
                if (it.listaStatements!!.isNotEmpty()) configurarStatementsRecyclerView(it.listaStatements)
                else setupEmptyList()
            }
        })

        mainViewModel.contaUsuario.observe(this, Observer { contaUsuario ->
            textview_nome.text = contaUsuario?.nome
            textview_conta.text =
                "${contaUsuario?.conta} / ${Mask().addMask(contaUsuario?.agencia!!, "##.######-#")}"
            textview_saldo.text = Converters().convertToCurrency(contaUsuario?.saldo!!)

            listStatements(idUsuario = contaUsuario!!.id!!)
        })
    }

    fun setupListeners() {
        viewProgressBar = progressBar
        logout_button.setOnClickListener {
            realizarLogout()
        }

    }

    private fun configurarStatementsRecyclerView(listaStatements: List<Statement>?) {
        val statementsRecyclerView = recyclerview_statements
        statementsRecyclerView.visibility = View.VISIBLE
        statementsRecyclerView.adapter = StatementsAdapter(listaStatements!!, application)
        val layoutManager = LinearLayoutManager(application)
        statementsRecyclerView.layoutManager = layoutManager
    }

    private fun setupEmptyList() {

        recyclerview_statements.visibility = View.GONE
        textview_semResultados.visibility = View.VISIBLE
    }

    private fun listStatements(idUsuario: Int) {
        doAsyncWork(work = { mainViewModel.listarStatements(idUsuario = idUsuario) })
    }

    fun realizarLogout() {
        sessionManager.limparDados()
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }
}