package com.example.appbanksantander_accenturetalentacquisition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementModel
import com.example.appbanksantander_accenturetalentacquisition.Presenter.Main.MainContract
import com.example.appbanksantander_accenturetalentacquisition.Presenter.Main.MainPresenter
import com.example.appbanksantander_accenturetalentacquisition.Utils.Adapters.StatementAdapter
import com.example.appbanksantander_accenturetalentacquisition.View.LoginActiviy

class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var statementListener: MainContract.UserLoads
    lateinit var statementList: RecyclerView
    lateinit var statementAdapter: StatementAdapter
    lateinit var nameUserTxt: TextView
    lateinit var accountUserTxt: TextView
    lateinit var balanceTxt: TextView
    lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        statementListener = MainPresenter(this, this)

        statementList = findViewById(R.id.statementList)
        nameUserTxt = findViewById(R.id.nameUserTxt)
        accountUserTxt = findViewById(R.id.accountUserTxt)
        balanceTxt = findViewById(R.id.balanceTxt)
        logoutButton = findViewById(R.id.logoutButton)

        val userId = intent!!.extras!!.getInt("userId")
        val nameUser = intent!!.extras!!.getString("name")
        val bankAccount = intent!!.extras!!.getString("bankAccount")
        val agency = intent!!.extras!!.getString("agency")
        val balance = intent!!.extras!!.getInt("balance")

        val account = bankAccount + "/" + agency
        val cents = ",00"
        val currency = "R$ "

        nameUserTxt.setText(nameUser)
        accountUserTxt.setText(account)
        balanceTxt.setText(currency + balance.toString() + cents)

        val initialCapacity = 0
        statementAdapter = StatementAdapter(ArrayList(initialCapacity),this)

        statementList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        statementList.adapter = statementAdapter

        statementListener.loadStatement(userId)

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActiviy::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun showStatement(statement: List<StatementModel>) {
        statementAdapter.setList(statement)
    }

}