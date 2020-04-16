package com.example.androidtest.presentation.home

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.R
import com.example.androidtest.utils.Constants
import com.example.androidtest.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mContext: Context
    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.mContext = this
        this.mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val nameUser = SharedPreference.get(this, Constants.PREF_NAME)
        val contUser = SharedPreference.get(this, Constants.PREF_CONT)

        textConta.text = contUser.replace("\"", "")
        textName.text = nameUser

        this.createObservers()
        buttonLogout.setOnClickListener(this)
    }

    private fun createObservers() {

        mHomeViewModel.list().observe(this, Observer {
            val arrayAdapter = StatementAdapter(this@HomeActivity, it)
            val headerView: View = layoutInflater.inflate(R.layout.listview_header, null)
            listView.adapter = arrayAdapter
            listView.addHeaderView(headerView)
            arrayAdapter.notifyDataSetChanged()
        })

        mHomeViewModel.toastNotification().observe(this, Observer {
            Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
            finish()
        })

        mHomeViewModel.saldo().observe(this, Observer {
            textSaldo.text = it
        })
    }

    override fun onClick(view: View?) {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Logout")
                setMessage("Deseja realmente sair do aplicativo ?")
                setIcon(android.R.drawable.ic_dialog_alert)
                setPositiveButton("Sim, obrigado!",
                    DialogInterface.OnClickListener { dialog, id ->
                        finish()
                    })
                setNegativeButton("Não",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }

            builder.create()
        }
        alertDialog?.show()

    }
}
