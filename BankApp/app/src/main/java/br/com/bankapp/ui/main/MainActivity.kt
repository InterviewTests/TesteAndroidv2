package br.com.bankapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.bankapp.BaseApplication
import br.com.bankapp.R
import br.com.bankapp.commons.Error
import br.com.bankapp.commons.Loading
import br.com.bankapp.commons.Success
import br.com.bankapp.data.utils.SharedPrefsHelper
import br.com.bankapp.ui.BaseActivity
import br.com.bankapp.ui.login.LoginActivity
import br.com.bankapp.utils.accountFormat
import br.com.bankapp.utils.brazilianFormat
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.annotations.TestOnly
import java.net.UnknownHostException
import javax.inject.Inject

class MainActivity: BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper
    private val statementsListAdapter: StatementsListAdapter by lazy {
        StatementsListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val mainComponent = (application as BaseApplication).appComponent
            .mainComponent().create()
        mainComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun initComponents(savedInstanceState: Bundle?) {
        statusBarColor(this, R.color.colorPrimary)
        statements_recyclerview.adapter = statementsListAdapter
        addListeners()
        getStatements(false)
    }

    override fun onStart() {
        super.onStart()
        subscribeObservers()
    }

    override fun getLayoutId() = R.layout.activity_main

    private fun addListeners() {
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh.setOnRefreshListener {
            getStatements(true)
        }
        exit_button.setOnClickListener {
            logout()
        }
    }

    private fun subscribeObservers() {
        viewModel.uiState.observe(this, Observer {
            when (it) {
                is Loading -> displayLoadingState()
                is Success -> {
                    hideLoadingState()
                    swipe_refresh.isRefreshing = false
                }
                is Error -> {
                    hideLoadingState()
                    swipe_refresh.isRefreshing = false
                    val text = if (it.error is UnknownHostException) {
                        getString(R.string.text_connection_unavailable)
                    } else {
                        getString(R.string.text_main_generic_error)
                    }
                    val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        })

        viewModel.getUserAccount(sharedPrefsHelper[SharedPrefsHelper.PREF_USER_ID, 0])
            .observe(this, Observer { userAccount ->
            userAccount?.let {
                name_text.text = it.name
                account_text.text = accountFormat(it.agency!!, it.bankAccount!!)
                balance_text.text = brazilianFormat(it.balance!!)
            }
        })

        viewModel.getStatements(sharedPrefsHelper[SharedPrefsHelper.PREF_USER_ID, 0])
            .observe(this, Observer { userStatements ->
                statementsListAdapter.submitList(userStatements)
        })
    }

    private fun displayLoadingState() {
        statements_recyclerview.visibility = View.GONE
        main_progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        statements_recyclerview.visibility = View.VISIBLE
        main_progress_bar.visibility = View.GONE
    }

    private fun getStatements(forceRefresh: Boolean) {
        viewModel.loadStatements(sharedPrefsHelper[SharedPrefsHelper.PREF_USER_ID, 0], forceRefresh)
    }

    private fun logout() {
        sharedPrefsHelper.removeKey(SharedPrefsHelper.PREF_USER_ID)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    @TestOnly
    fun setTestViewModel(testViewModel: MainViewModel) {
        viewModel = testViewModel
        sharedPrefsHelper.put(SharedPrefsHelper.PREF_USER_ID, 1)
    }

}