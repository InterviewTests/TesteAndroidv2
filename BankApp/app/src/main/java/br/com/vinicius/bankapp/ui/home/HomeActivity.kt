package br.com.vinicius.bankapp.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.*
import br.com.vinicius.bankapp.ui.login.LoginActivity

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var presenter: HomePresenter
    private lateinit var user:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadExtras()
        loadUI()
        loadFonts()
        logout()
        actionRefresh()
        user.userId?.let { id -> presenter.fetchListStatements(id) }
    }

    override fun onStart() {
        super.onStart()
        presenter.validateNetwork(this)

    }

    private fun actionRefresh() {
        swipeRefreshLayoutStatement.setOnRefreshListener {
            user.userId?.let { id -> presenter.fetchListStatements(id) }
        }
    }

    override fun showProgressRecycler(show: Boolean) {
        swipeRefreshLayoutStatement.isRefreshing = show
    }

    private fun loadExtras() {
        user = intent.getSerializableExtra(USER_ACCOUNT) as User
    }

    @SuppressLint("SetTextI18n")
    private fun loadUI() {
        presenter = HomePresenter(this@HomeActivity)
        textViewName.text = user.name
        textViewAccountNumber.text = "${user.bankAccount} / ${user.agency?.let {
            Formation.accountFormat(it) }}"
        textViewBalanceNumber.text = "${user.balance?.let { Formation.currencyFormat(it)}}"
    }

    private fun loadFonts() {
        val fontLight = Typeface.createFromAsset(assets, FONT_HELVETICANEUELIGHT)
        val fontNormal = Typeface.createFromAsset(assets, FONT_HELVETICANEUE)
        textViewName.typeface = fontLight
        textViewAcountName.typeface = fontNormal
        textViewAccountNumber.typeface = fontLight
        textViewBalanceName.typeface = fontNormal
        textViewBalanceNumber.typeface = fontLight
    }

    override fun notification(message: String) {
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun initRecyclerView(models: List<StatementModel>) {

        recyclerViewStatement.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = HomeAdapterStatement(this@HomeActivity, models)
        }

    }

    override fun logout(){
        imageViewLogout.setOnClickListener {
            val intentHome = Intent(this, LoginActivity::class.java)
            Preferences.clearPreferences()
            startActivity(intentHome)
            finish()
        }
    }

    override fun getActivity(): AppCompatActivity = this

}
