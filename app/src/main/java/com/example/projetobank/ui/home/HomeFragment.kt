package com.example.projetobank.ui.home

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
import kotlinx.android.synthetic.main.layout_dados_usuario.*
import com.example.projetobank.R


class HomeFragment : Fragment(), HomeContrato.View {

    override lateinit var presenter: HomeContrato.Presenter
    private lateinit var dadoObjeto: userAccount

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
      //  presenter.start()
        if (arguments != null) {
            dadoObjeto = arguments!!.getParcelable("dadosBancario")
          //  adicionarDadosBancarioDoUsuario(dadoObjeto)
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    private fun adicionarDadosBancarioDoUsuario(dados: userAccount) {
        til_conta_label.text =dados.bankAccount
        tv_user_login.text = dados.name
        til_Saldo_label.text = dados.balance.toString()
    }

    override fun exibeInformacao(mensagem: String) {
//        activity?.let {
//            val alertDialogFragment = AlertDialogFragment()
//            alertDialogFragment.setMensagem(mensagem)
//            alertDialogFragment.setBotaoNeutro("ok") {
//                alertDialogFragment.dismiss()
//            }
//            alertDialogFragment.show(pegaFragmentTranscation(), TAG_DIALOG)
//        }
    }

    override fun listarStatement(statement: List<Statement>) {
        Log.e("statement ",statement[0].toString())
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
