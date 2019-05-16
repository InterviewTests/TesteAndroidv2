package com.example.projetobank.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projetobank.R
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.source.DadosBancarioRepositorio
import com.example.projetobank.data.source.remote.DadosBancarioRemoteDataSource
import com.example.projetobank.util.AppExecutors
import com.example.projetobank.util.replaceFragmentInActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var presenter: HomePresenter
    private lateinit var dadoObjeto: userAccount

    companion object {
        const val TAG_USUARIO = "usuario"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var data = intent.extras

        val name = data!!.getString("name")
        val userId = data.getInt("userId")
        val bankAccount = data.getString("bankAccount")
        val balance = data.getDouble("balance")
        val agency = data.getString("agency")
        dadoObjeto  = userAccount(agency,balance,bankAccount,name,userId)

        val homeFragment = supportFragmentManager.findFragmentById(
                R.id.homeContentFrame
        ) as HomeFragment? ?: HomeFragment
                .newInstance(dadoObjeto).also {
            replaceFragmentInActivity(it, R.id.homeContentFrame)
        }

        val dadosBancario = DadosBancarioRemoteDataSource.getInstance(
                AppExecutors()
        )

        val repositorio = DadosBancarioRepositorio(dadosBancario)
        presenter = HomePresenter(repositorio, homeFragment)
    }
}
