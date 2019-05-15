package com.example.projetobank.ui.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.projetobank.R
import com.example.projetobank.data.model.Statement
import com.example.projetobank.ui.login.LoginFragment


class HomeFragment : Fragment(), HomeContrato.View {

    override lateinit var presenter: HomeContrato.Presenter

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        presenter.start()
        return root
    }

    override fun exibeInformacao(mensagem: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listarStatement(statement: List<Statement>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configuraUrlRetrofit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()

    }
}
