package com.br.natanfelipe.bankapp.view.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.br.natanfelipe.bankapp.R
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.configurator.HomeConfigurator
import com.br.natanfelipe.bankapp.interfaces.home.HomeActivityIntput
import com.br.natanfelipe.bankapp.model.Statement
import com.br.natanfelipe.bankapp.model.UserAccount
import com.br.natanfelipe.bankapp.view.adapter.ItemBankAdapter
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import com.br.natanfelipe.bankapp.interfaces.home.HomeInteractorInput

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.view.*
import java.util.*

class HomeActivity : AppCompatActivity(), HomeActivityIntput {


    var billsList = mutableListOf<Statement>()
    lateinit var adapter: ItemBankAdapter
    lateinit var api : RestApi
    var output : HomeInteractorInput? = null
    lateinit var name : String
    lateinit var userAccount : UserAccount

    override fun displayHomeMetaData(viewModel: HomeViewModel) {
        billsList = viewModel.billsList
        createBillsList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val intent = intent
        val currency = Currency.getInstance(Locale.getDefault())
        if(intent.getSerializableExtra("userAccount") != null){
            userAccount = intent.getSerializableExtra("userAccount") as UserAccount
            tv_name.text = userAccount.name
            tv_agency.text = userAccount.bankAccount+" / "+userAccount.agency.toString()
            tv_balance.text = currency.symbol+""+userAccount.balance.toString()
        }

        HomeConfigurator.INSTANCE.configure(this)
        api = RestApi()
        fetchMetaData()

        iv_desc.setOnClickListener{
            disconect()
        }

    }

    private fun disconect() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("logout",true)
        finish()
        startActivity(intent)
    }

    private fun createBillsList() {
        content.rv.layoutManager = LinearLayoutManager(this)
        adapter = ItemBankAdapter(this,billsList)
        content.rv.adapter = adapter
        content.progressBar.visibility = View.GONE
        content.rv.visibility = View.VISIBLE
        content.textView.visibility = View.VISIBLE
    }

    fun fetchMetaData(){
        val homeRequest = HomeRequest()
        homeRequest.api = api
        output?.fetchHomeMetaData(homeRequest)
    }

}
