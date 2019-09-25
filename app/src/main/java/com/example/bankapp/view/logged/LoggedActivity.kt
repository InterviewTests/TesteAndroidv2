package com.example.bankapp.view.logged

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankapp.R
import com.example.bankapp.adapter.AdapterListStatements
import com.example.bankapp.model.Statement
import com.example.bankapp.model.User
import kotlinx.android.synthetic.main.logged.*

class LoggedActivity: AppCompatActivity(), LoggedContract.View {

    lateinit var mPresenter: LoggedContract.Presenter
    private val linearLayoutManager = LinearLayoutManager(this)
    lateinit var mAdapter: AdapterListStatements

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logged)
        mPresenter = LoggedPresenter(this)

        if (intent.hasExtra(BUNDLE)){
            val bundle = intent.getBundleExtra(BUNDLE)
            val user = bundle.getParcelable(USER) as User
            txtNome.text = user.name
            txtNumberConta.text = "${user.bankAccount} / ${user.agency}"
            txtValorSaldo.text = user.balance.toString()

            mPresenter.getStatementList(user)
        }

        btnLogout.setOnClickListener { askLogout() }
    }

    override fun updateStatements(statments: List<Statement>) {
        mAdapter = AdapterListStatements(statments as ArrayList<Statement>)

        listStatements.apply {
            layoutManager = linearLayoutManager
            adapter = mAdapter
        }
    }

    override fun askLogout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.logout))
        builder.setMessage(getString(R.string.deseja_deslogar))
            .setPositiveButton(getString(R.string.sim)) { _, _ ->
                mPresenter.logOut()
            }
            .setNegativeButton(getString(R.string.nao)) { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
        builder.show()
    }

    override fun exit(){
        finish()
    }

    fun askExit(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(getString(R.string.fechar_aplicacao))
            .setPositiveButton(getString(R.string.sim)) { _, _ ->
                finish()
                moveTaskToBack(true)
            }
            .setNegativeButton(getString(R.string.nao)) { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
        builder.show()
    }

    override fun onBackPressed() {
        askExit()
    }

    companion object{
        const val USER = "USER"
        const val BUNDLE = "BUNDLE"
    }
}