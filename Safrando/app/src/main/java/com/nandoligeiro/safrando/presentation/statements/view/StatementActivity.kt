package com.nandoligeiro.safrando.presentation.statements.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandoligeiro.safrando.databinding.ActivityStatementBinding
import com.nandoligeiro.safrando.presentation.common.Constants
import com.nandoligeiro.safrando.presentation.statements.state.UiBankStatementState
import com.nandoligeiro.safrando.presentation.login.model.UiLoginModel
import com.nandoligeiro.safrando.presentation.statements.model.UiStatementModel
import com.nandoligeiro.safrando.presentation.statements.view.adapter.BankStatementsAdapter
import com.nandoligeiro.safrando.presentation.statements.viewmodel.StatementViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StatementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatementBinding
    private val viewModel: StatementViewModel by viewModels()
    private val bankStatementsAdapter by lazy { BankStatementsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindView()
        setRecyclerView()
        observeVMEvents()
        getBankStatement()
    }

    private fun setRecyclerView() {
        binding.recyclerView.run {
            adapter = bankStatementsAdapter
            layoutManager = LinearLayoutManager(this@StatementActivity)
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
        }
    }

    private fun getBankStatement() {
        getUiLoginModelExtra()?.let {
            viewModel.getBankStatement(it.id)
        }
    }

    private fun observeVMEvents() {
        lifecycleScope.launch {
            viewModel.bankStatement.collect { uiState ->
                when (uiState) {
                    is UiBankStatementState.Loading -> {
                        showLoading()
                    }

                    is UiBankStatementState.Success -> {
                        notifyAdapter(uiState.data)
                    }

                    is UiBankStatementState.Error -> {
                        //handleError()
                    }
                }
            }
        }
    }

    private fun getUiLoginModelExtra() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.KeyExtra.KEY_EXTRA_LOGIN, UiLoginModel::class.java)
        } else {
            intent.getParcelableExtra(Constants.KeyExtra.KEY_EXTRA_LOGIN)
        }

    private fun bindView() {
        binding.run {
            getUiLoginModelExtra()?.let { userData ->
                val agencyAndAccount = "${userData.agency} / ${userData.account}"
                headerName.text = userData.name
                account.text = agencyAndAccount
                balance.text = userData.balance
            }
        }
    }

    private fun notifyAdapter(data: List<UiStatementModel>) {
        hideLoading()
        bankStatementsAdapter.submitList(data)
    }

    private fun showLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.userListProgressBar.visibility = View.GONE
    }
}
