package com.paulokeller.bankapp.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paulokeller.bankapp.R
import com.paulokeller.bankapp.models.Account
import com.paulokeller.bankapp.models.AppState
import com.paulokeller.bankapp.models.UserAccount
import com.paulokeller.bankapp.repositories.Repository
import com.paulokeller.bankapp.statements.StatementsActivity
import com.paulokeller.bankapp.utils.Utils
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        repository = Repository(activity?.baseContext)

        userTextField.setText(repository.getUser())

        setupObserver()

        loginButton.setOnClickListener {
            val password = passwordTextField.text.toString()
            val user = userTextField.text.toString()
            viewModel.login(user, password)
        }
    }

    private fun setupObserver() {
        val resultObserver = Observer<AppState<Account>> { result ->
            if (result.errorMessage != null) {
                Toast.makeText(activity, result.errorMessage, Toast.LENGTH_LONG).show()
            } else {
                val state = result.data as Account
                navigateToStatementsActivity(state.userAccount)
            }
        }

        viewModel.loginState.observe(viewLifecycleOwner, resultObserver)
    }

    private fun navigateToStatementsActivity(userAccount: UserAccount) {
        val account = userAccount.bankAccount + " / " + userAccount.agency
        val formattedBalance = Utils.formatCurrency(userAccount.balance.toFloat())
        val intent = Intent(activity, StatementsActivity::class.java)
        intent.putExtra("name", userAccount.name)
        intent.putExtra("account", account)
        intent.putExtra("balance", formattedBalance)

        startActivity(intent)
        activity?.finish()
    }

}
