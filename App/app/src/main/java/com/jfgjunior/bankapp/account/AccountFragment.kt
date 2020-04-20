package com.jfgjunior.bankapp.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jfgjunior.bankapp.R
import com.jfgjunior.bankapp.data.models.Statement
import com.jfgjunior.bankapp.data.models.User
import com.jfgjunior.bankapp.setValueAsCurrency
import com.jfgjunior.bankapp.shouldBeVisible
import kotlinx.android.synthetic.main.account_info.*
import kotlinx.android.synthetic.main.fragment_account.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AccountFragment : Fragment() {

    private val accountViewModel by viewModel<AccountViewModel> {
        parametersOf(arguments?.let {
            AccountFragmentArgs.fromBundle(it).user
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingIndicator.shouldBeVisible(true)
        observeEvents()
        setupListener()
    }

    override fun onDestroy() {
        accountViewModel.onDestroy()
        super.onDestroy()
    }

    private fun setupRecyclerViewAdapter(statements: List<Statement>) {
        if (transactionRecyclerView.adapter == null) {
            transactionRecyclerView.adapter = StatementAdapter()
        }
        (transactionRecyclerView.adapter as StatementAdapter).submitList(statements)
    }

    private fun bindModelToView(user: User) {
        name.text = user.name
        accountNumber.text = user.bankAccountAndAgency
        balanceValue.setValueAsCurrency(user.balance)
    }

    private fun setupListener() {
        logoutImage.setOnClickListener {
            accountViewModel.clearUser()
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }
    }

    private fun observeEvents() {
        with(accountViewModel) {
            userAccount.observe(
                viewLifecycleOwner,
                Observer { bindModelToView(it) })

            statementListSuccess.observe(viewLifecycleOwner, Observer {
                loadingIndicator.shouldBeVisible(false)
                transactionRecyclerView.shouldBeVisible(true)
                errorMessage.shouldBeVisible(false)
                setupRecyclerViewAdapter(it)
            })

            statementListFail.observe(viewLifecycleOwner, Observer { error ->
                loadingIndicator.shouldBeVisible(false)
                transactionRecyclerView.shouldBeVisible(false)
                errorMessage.shouldBeVisible(true)
                errorMessage.text =
                    if (error.message.isBlank()) resources.getString(R.string.unknown_error) else error.message
            })
        }
    }
}