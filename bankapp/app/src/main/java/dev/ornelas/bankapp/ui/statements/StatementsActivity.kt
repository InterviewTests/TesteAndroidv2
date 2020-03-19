package dev.ornelas.bankapp.ui.statements

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ornelas.bankapp.R
import dev.ornelas.bankapp.commons.formatToCurrency
import dev.ornelas.bankapp.ui.login.LoginRouter
import dev.ornelas.bankapp.ui.login.UserViewModel
import dev.ornelas.bankapp.ui.statements.adapter.StatementsAdapter
import kotlinx.android.synthetic.main.activity_statements.*
import kotlinx.android.synthetic.main.content_statements.*

class StatementsActivity : AppCompatActivity(), StatementsContract.View {

    lateinit var presenter: StatementsContract.Presenter
    lateinit var router: StatementsContract.Router
    lateinit var statementsAdapter: StatementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        setSupportActionBar(toolbar)

        StatementsConfigurator.configure(this)

        val userViewModel = intent.getParcelableExtra(LoginRouter.APP_USER_INTENT) as UserViewModel

        userViewModel.let {
            accountValue.text = getString(R.string.agency_account, it.bankAccount, it.agency)
            balanceValue.text = it.balance.formatToCurrency()

            showSpinner(true)
            presenter.onLoadStatments(it.id)
        }

        logoutButton.setOnClickListener {
            presenter.onLogout()
        }

    }

    private fun populateRecyclerView(statementsViewModel: List<StatementViewModel>) {
        statementsList.apply {
            layoutManager = LinearLayoutManager(this@StatementsActivity)
            statementsAdapter.statementsViewModel = statementsViewModel
            adapter = statementsAdapter.apply { updateAdapter(statementsViewModel) }
        }
    }

    private fun showSpinner(isLoading: Boolean) {
        loading.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showError(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun displayStatements(statementsViewModel: StatementsViewModel) {

        showSpinner(false)

        if (statementsViewModel.logedOut) {
            router.navigateToLogin()
            return
        }

        if (statementsViewModel.errorMessage != null) {
            showError(statementsViewModel.errorMessage)
        } else {
            statementsViewModel.statements?.let { populateRecyclerView(it) }
        }
    }
}
