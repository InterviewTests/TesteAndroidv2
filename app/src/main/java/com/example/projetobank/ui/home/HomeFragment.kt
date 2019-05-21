package com.example.projetobank.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetobank.data.model.Statement
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.source.RetrofitInicializador
import kotlinx.android.synthetic.main.fragment_home.view.*
import com.example.projetobank.R
import com.example.projetobank.ui.login.LoginActivity
import com.example.projetobank.ui.widget.AlertDialogFragment
import com.example.projetobank.util.TAG_DIALOG
import com.example.projetobank.util.pegaFragmentTranscation
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class HomeFragment : Fragment(), HomeContrato.View {

    override lateinit var presenter: HomeContrato.Presenter
    private lateinit var dadoObjeto: userAccount

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        if (arguments != null) {
            dadoObjeto = arguments!!.getParcelable("dadosBancario")
        }
        irTelaLogin()
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        adicionarDadosBancarioDoUsuario(dadoObjeto)
    }

    private fun adicionarDadosBancarioDoUsuario(dados: userAccount) {
        til_conta_value.text = dados.bankAccount
        tv_user_login.text = dados.name
        til_saldo_value.text = dados.balance.toString()
    }

    private fun irTelaLogin() {
        with(root) {
            ib_logout.setOnClickListener {
                logout()
            }

        }
    }

    private fun logout() {
        activity?.let {
            val alertDialogFragment = AlertDialogFragment()
            alertDialogFragment.setTitulo("Aviso")
            alertDialogFragment.setMensagem("Deseja realmente sair do aplicativo?")
            alertDialogFragment.setBotaoPositivo("ok") {
                val loginIntent = Intent(context, LoginActivity::class.java)
                startActivity(loginIntent)
                it.finish()
            }
            alertDialogFragment.setBotaoNegativo("Cancelar") {
                alertDialogFragment.dismiss()
            }
            alertDialogFragment.show(pegaFragmentTranscation(), TAG_DIALOG)
        }
    }

    override fun exibeInformacao(mensagem: String) {
        activity?.let {
            val alertDialogFragment = AlertDialogFragment()
            alertDialogFragment.setMensagem(mensagem)
            alertDialogFragment.setBotaoNeutro("ok") {
                alertDialogFragment.dismiss()
            }
            alertDialogFragment.show(pegaFragmentTranscation(), TAG_DIALOG)
        }
    }

    override fun listarStatement(statement: List<Statement>) {
        Log.e("statement ", statement[0].toString())
        val adapter = HomeAdapter(statement, requireContext())
        root.home_statement_recycler.adapter = adapter
    }

    override fun configuraUrlRetrofit() {
        RetrofitInicializador.url = "https://bank-app-test.herokuapp.com/api/"
    }

    companion object {
        @JvmStatic
        fun newInstance(objeto: userAccount?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putParcelable("dadosBancario", objeto)
            fragment.arguments = args
            return fragment
        }

    }
}
