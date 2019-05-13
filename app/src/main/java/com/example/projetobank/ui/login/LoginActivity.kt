package com.example.projetobank.ui.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projetobank.R
import com.example.projetobank.data.source.UsuarioDataSource
import com.example.projetobank.data.source.UsuarioRepositorio
import com.example.projetobank.data.source.local.AppDataBase
import com.example.projetobank.data.source.local.UsuarioLocalDataSource
import com.example.projetobank.data.source.remote.UsuarioRemoteDataSource
import com.example.projetobank.util.AppExecutors
import com.example.projetobank.util.replaceFragmentInActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager.findFragmentById(
            R.id.loginContentFrame
        ) as LoginFragment? ?: LoginFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.loginContentFrame)
            }

        val localDB = AppDataBase.getInstance(applicationContext)

        val localDataSource = UsuarioLocalDataSource.getInstance(
            AppExecutors(),
            localDB.usuarioDao()
        )

        val repositorio = UsuarioRepositorio.getInstance(
            localDataSource,
            UsuarioRemoteDataSource.getInstance(AppExecutors())
        )
        presenter = LoginPresenter(repositorio, fragment)
    }



}
