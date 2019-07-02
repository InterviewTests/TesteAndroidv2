package io.github.maikotrindade.bankapp.statement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.base.util.StringsUtil.convertDoubleToCurrency
import io.github.maikotrindade.bankapp.login.domain.LoginRouter.Companion.navLoginStatements
import io.github.maikotrindade.bankapp.login.model.UserData
import io.github.maikotrindade.bankapp.statement.domain.StatementsConfigurator
import io.github.maikotrindade.bankapp.statement.domain.StatementsInteractor
import io.github.maikotrindade.bankapp.statement.domain.StatementsRouter
import io.github.maikotrindade.bankapp.statement.model.Statement
import kotlinx.android.synthetic.main.fragment_statement_list.*

interface StatementsFragmentInput {
    fun updateStatementsList(statements: List<Statement>)
    fun redirectToLogin()
    fun showError(errorMessage: String? = null)
}

class StatementsListFragment : Fragment(), StatementsFragmentInput {

    var interactor: StatementsInteractor? = null
    var router: StatementsRouter? = null
    var listAdapter: StatementsListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statement_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userData = arguments?.getParcelable<UserData>(navLoginStatements)
        StatementsConfigurator.INSTANCE.configure(this)
        configureUserData(userData)
        bindUI()
        interactor?.getStatements(userData?.userId!!)
    }

    private fun configureUserData(userData: UserData?) {
        txtName.text = userData?.name
        txtBankAccount.text = "${userData?.bankAccount} / ${userData?.agency}"
        txtBalance.text = convertDoubleToCurrency(userData?.balance!!)
    }

    private fun bindUI() {
        rvStatements.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvStatements.setHasFixedSize(true)
        listAdapter = StatementsListAdapter(emptyList())
        rvStatements.adapter = listAdapter

        imgLogout.setOnClickListener {
            interactor?.presenter?.logout()
        }
    }

    override fun updateStatementsList(statements: List<Statement>) {
        listAdapter?.statements = statements
        listAdapter?.notifyDataSetChanged()
    }

    override fun redirectToLogin() {
        router?.navigateToLoginScreen()
    }

    override fun showError(errorMessage: String?) {
        val errorMessageString = errorMessage ?: getString(R.string.error_statements)
        val errorSnackbar = Snackbar.make(containerView, errorMessageString, Snackbar.LENGTH_LONG)
        errorSnackbar.show()
    }

}
