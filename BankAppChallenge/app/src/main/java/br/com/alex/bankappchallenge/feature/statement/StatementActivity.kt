package br.com.alex.bankappchallenge.feature.statement

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alex.bankappchallenge.R
import br.com.alex.bankappchallenge.extensions.observeNonNull
import br.com.alex.bankappchallenge.model.FormatedStatement
import br.com.alex.bankappchallenge.model.FormatedUserAccount
import kotlinx.android.synthetic.main.activity_statements.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementActivity : AppCompatActivity() {

    private val statementAdapter = StatementAdapter()
    private val statementViewModel: StatementViewModel by viewModel()
    private val statementRouter = StatementRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        lifecycle.addObserver(statementViewModel)
        setupRecyclerView()
        initViewModel()
        actionButtonLogout()
    }

    private fun setupRecyclerView() {
        with(recyclerViewStatements) {
            adapter = statementAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun actionButtonLogout() {
        imageViewLogout.setOnClickListener {
            statementViewModel.execute(StatementIntentions.Logout)
        }
    }

    private fun initViewModel() {
        statementViewModel.statesStatement.observeNonNull(this) {
            with(it) {
                when {
                    isLoadError -> showError(errorMessage)
                    isLoadingStatement -> showLoadingStatement()
                    statementList.isNotEmpty() -> fillStatementList(statementList)
                }
            }
        }

        statementViewModel.stateUserAccount.observeNonNull(this) {
            with(it) {
                when {
                    isLoadingUserAccount -> showLoadingUserAccount()
                    userAccount != null -> fillUserAccountData(userAccount)
                }
            }
        }

        statementViewModel.navigations.observeNonNull(this) {
            it.getContentIfNotHandled()?.let {
                statementRouter.navigateBackToLogin()
            }
        }
    }

    private fun fillStatementList(formatedStatementList: List<FormatedStatement>) {
        statementAdapter.statementList = formatedStatementList
    }

    private fun fillUserAccountData(formatedUserAccount: FormatedUserAccount) {
        textViewClientName.text = formatedUserAccount.name
        textViewBankAccount.text = formatedUserAccount.bankAndAgency
        textViewBalance.text = formatedUserAccount.balance
    }

    private fun showLoadingUserAccount() {
        Log.i("StatementActivity", "showLoadingUserAccount()")
    }

    private fun showLoadingStatement() {
        Log.i("StatementActivity", "showLoadingStatement()")
    }

    private fun showError(errorMessage: String) {
        Log.i("StatementActivity", "showError($errorMessage)")
    }
}