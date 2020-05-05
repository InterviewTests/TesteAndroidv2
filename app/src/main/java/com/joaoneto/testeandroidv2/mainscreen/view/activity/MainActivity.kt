package com.joaoneto.testeandroidv2.mainscreen.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.joaoneto.testeandroidv2.R
import com.joaoneto.testeandroidv2.ViewModelProviderFactory
import com.joaoneto.testeandroidv2.databinding.ActivityMainBinding
import com.joaoneto.testeandroidv2.loginscreen.model.UserAccountModel
import com.joaoneto.testeandroidv2.loginscreen.view.activity.LoginActivity
import com.joaoneto.testeandroidv2.mainscreen.model.StatementModel
import com.joaoneto.testeandroidv2.mainscreen.view.adapter.StatementsAdapter
import com.joaoneto.testeandroidv2.mainscreen.viewModel.MainViewModel
import com.joaoneto.testeandroidv2.util.system.Formatter
import com.joaoneto.testeandroidv2.util.system.SnackbarHelper
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val userAccount = intent.extras?.getSerializable("userAccountData") as UserAccountModel
        binding.userAccount = userAccount
        binding.formatter = Formatter()

        viewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        setUpAppBar()
        setUpView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logout()

    }


    private fun setUpView() {
        logoutImageView.setOnClickListener {
            logout()
        }

        viewModel.getStatements().observe(this, Observer {

            if (it == null) {
                SnackbarHelper.message(
                    mainConstraint,
                    "Não foi possivel buscar as transações, tente novamente mais tarde"
                )
            } else {
                it.statementList?.let { statementList -> setUpRecyclerView(statementList) }
            }
        })

    }

    private fun setUpAppBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        toolbar.setNavigationOnClickListener {
        }
        collapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                this,
                R.color.colorWhite
            )
        )
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOfSet ->
            if (verticalOfSet == 0) {
                appbarConstraint.visibility = View.VISIBLE
                collapsingToolbar.title = " "
            } else {
                appbarConstraint.visibility = View.INVISIBLE
                collapsingToolbar.title = "Bank"

            }
        })
    }

    private fun setUpRecyclerView(statements: List<StatementModel>) {
        operationsRecyclerView.adapter =
            StatementsAdapter(
                statements
            )
        operationsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}
