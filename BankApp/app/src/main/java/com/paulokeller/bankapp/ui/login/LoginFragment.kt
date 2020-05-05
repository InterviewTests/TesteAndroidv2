package com.paulokeller.bankapp.ui.login

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
import com.paulokeller.bankapp.data.models.Account
import com.paulokeller.bankapp.data.models.AppState
import com.paulokeller.bankapp.data.models.UserAccount
import com.paulokeller.bankapp.ui.statements.StatementsActivity
import com.paulokeller.bankapp.utils.Utils
import kotlinx.android.synthetic.main.login_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class LoginFragment : Fragment(), KodeinAware {
    override val kodeinContext = kcontext<Fragment> (this)
    override val kodein by closestKodein()

    private val viewModelFactory: LoginViewModelFactory by instance()

    companion object {
        fun newInstance() = LoginFragment()
    }
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        kodeinTrigger?.trigger()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

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
