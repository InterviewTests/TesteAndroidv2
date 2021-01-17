package com.jeanjnap.bankapp.ui.statements

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.jeanjnap.bankapp.R
import com.jeanjnap.bankapp.databinding.ActivityStatementsBinding
import com.jeanjnap.bankapp.ui.base.BaseActivity
import com.jeanjnap.bankapp.util.extension.observe
import com.jeanjnap.domain.entity.UserAccount
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementsActivity : BaseActivity() {

    override val viewModel: StatementsViewModel by viewModel()

    private lateinit var binding: ActivityStatementsBinding
    private lateinit var statementsAdapter: StatementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statements)
        binding.viewModel = viewModel

        (intent.extras?.getSerializable(USER_ACCOUNT_EXTRA) as? UserAccount)?.let {
            binding.userAccount = it
        }
        setupAdapter()
        listenUi()
    }

    private fun setupAdapter() {
        statementsAdapter = StatementsAdapter()
        binding.rvStatements.adapter = statementsAdapter
    }

    private fun listenUi() {
        observe(viewModel.loading, ::onLoading)
        observe(viewModel.statements, statementsAdapter::submitList)
    }

    private fun onLoading(isLoading: Boolean) {
        binding.slLoading.isVisible = isLoading
        binding.rvStatements.isVisible = !isLoading
    }

    companion object {
        private const val USER_ACCOUNT_EXTRA = "userAccount"

        fun clearTopStart(context: Context, userAccount: UserAccount) {
            context.startActivity(
                Intent(context, StatementsActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra(USER_ACCOUNT_EXTRA, userAccount)
                }
            )
        }
    }
}
