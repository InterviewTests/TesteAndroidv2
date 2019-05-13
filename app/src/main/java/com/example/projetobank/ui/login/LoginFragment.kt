package com.example.projetobank.ui.login

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetobank.R
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.source.RetrofitInicializador
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

    private  fun pegarLogin (): Usuario? {
        with(root) {
            val usuario = til_usuario_autenticacao.editText?.text.toString()
            val senha = til_senha_autenticacao.editText?.text.toString()
            return Usuario(usuario, senha)
        }
    }
    override fun vaiParaHome() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exibe(mensagem: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exibeProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun escondeProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configuraUrlRetrofit() {
        RetrofitInicializador.url ="https://bank-app-test.herokuapp.com/api/"
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
