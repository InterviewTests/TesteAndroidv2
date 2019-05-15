package com.example.projetobank.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projetobank.R
import com.example.projetobank.data.source.UsuarioRepositorio
import com.example.projetobank.data.source.local.AppDataBase
import com.example.projetobank.data.source.local.UsuarioLocalDataSource
import com.example.projetobank.data.source.remote.DadosBancarioRemoteDataSource
import com.example.projetobank.data.source.remote.UsuarioRemoteDataSource
import com.example.projetobank.util.AppExecutors
import com.example.projetobank.util.replaceFragmentInActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var presenter: HomePresenter

    companion object {
        const val TAG_USUARIO = "usuario"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame) as HomeFragment? ?: HomeFragment
            .newInstance().also{
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }


        val localDB = AppDataBase.getInstance(applicationContext)

        val usuarioLocalDataSource = UsuarioLocalDataSource.getInstance(
            AppExecutors(),
            localDB.usuarioDao()
        )

        val usuarioRemoteDataSource = UsuarioRemoteDataSource.getInstance(
            AppExecutors()
        )


//        val dadosRemoteDataSource = DadosBancarioRemoteDataSource.getInstance(
//            AppExecutors()
//        )

        val repositorio = UsuarioRepositorio.getInstance(usuarioLocalDataSource,usuarioRemoteDataSource)

      //  presenter = HomePresenter(repositorio, homeFragment)
    }
}
