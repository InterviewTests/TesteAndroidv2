package com.example.projetobank.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetobank.R
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.source.RetrofitInicializador
import com.example.projetobank.ui.home.HomeActivity
import com.example.projetobank.ui.widget.AlertDialogFragment
import com.example.projetobank.util.TAG_DIALOG
import com.example.projetobank.util.pegaFragmentTranscation
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment(), LoginContrato.View {

    override lateinit var presenter: LoginContrato.Presenter

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_login, container, false)
        presenter.start()
        configurarAcesso()
        return root
    }

    private fun configurarAcesso() {
        with(root) {
            btn_login.setOnClickListener {
                autentica()
            }

        }
    }

    private fun autentica() {
        presenter.autentica(pegarLogin())
    }

    private fun pegarLogin(): Usuario {
        with(root) {
            val usuario = til_usuario_autenticacao.editText?.text.toString()
            val senha = til_senha_autenticacao.editText?.text.toString()
            return Usuario(usuario, senha)
        }
    }

    override fun informaErroDeValidacao(loginCampo: LoginCampo) {
        with(root) {
            when (loginCampo) {
                LoginCampo.USUARIO ->
                    til_usuario_autenticacao.error = resources.getString(R.string.login_msg_erro_usuario)
                LoginCampo.SENHA ->
                    til_senha_autenticacao.error = resources.getString(R.string.login_msg_erro_senha)
            }
        }
    }


    override fun vaiParaHome(dados : userAccount) {
        activity?.let {
            val homeIntent = Intent(context, HomeActivity::class.java)
            Log.e("dadosBancario", dados.name)

            homeIntent.putExtra("name",dados.name)
            homeIntent.putExtra("userId",dados.userId)
            homeIntent.putExtra("bankAccount",dados.bankAccount)
            homeIntent.putExtra("balance",dados.balance)
            homeIntent.putExtra("agency",dados.agency)
            startActivity(homeIntent)
            it.finish()
        }
    }

    override fun exibe(mensagem: String) {
        activity?.let {
            val alertDialogFragment = AlertDialogFragment()
            alertDialogFragment.setMensagem(mensagem)
            alertDialogFragment.setBotaoNeutro(resources.getString(R.string.ok)) {
                alertDialogFragment.dismiss()
            }
            alertDialogFragment.show(pegaFragmentTranscation(), TAG_DIALOG)
        }
    }

    override fun exibeProgressBar() {
        with(root){
            logino_layout_botao_avancar.isClickable = false
            btn_login.visibility = View.GONE
            login_progress_bar.visibility = View.VISIBLE
        }
    }

    override fun escondeProgressBar() {
        with(root){
            logino_layout_botao_avancar.isClickable = true
            btn_login.visibility = View.VISIBLE
            login_progress_bar.visibility = View.GONE
        }
    }

    override fun configuraUrlRetrofit() {
        RetrofitInicializador.url = "https://bank-app-test.herokuapp.com/api/"
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()

    }
}
