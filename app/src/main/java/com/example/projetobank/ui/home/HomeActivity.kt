package com.example.projetobank.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projetobank.R
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.source.DadosBancarioRepositorio
import com.example.projetobank.data.source.UsuarioRepositorio
import com.example.projetobank.data.source.local.AppDataBase
import com.example.projetobank.data.source.local.UsuarioLocalDataSource
import com.example.projetobank.data.source.remote.DadosBancarioRemoteDataSource
import com.example.projetobank.data.source.remote.UsuarioRemoteDataSource
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
        val args = Bundle()
        dadoObjeto = args.getParcelable("dadosBancario")
        val homeFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as HomeFragment? ?: HomeFragment
            .newInstance(dadoObjeto).also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }


        val localDB = AppDataBase.getInstance(applicationContext)

        val usuarioLocalDataSource = UsuarioLocalDataSource.getInstance(
            AppExecutors(),
            localDB.usuarioDao()
        )

        val dadosBancario = DadosBancarioRemoteDataSource.getInstance(
            AppExecutors()
        )

        val repositorio = DadosBancarioRepositorio(dadosBancario)
        presenter = HomePresenter(repositorio, homeFragment)
    }
}
