package com.nandoligeiro.safrando.presentation.statements.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nandoligeiro.safrando.databinding.StatementCardBinding
import com.nandoligeiro.safrando.presentation.statements.model.UiStatementModel


class BankStatementItemViewHolder(
    private val binding: StatementCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): BankStatementItemViewHolder {
            val binding =
                StatementCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BankStatementItemViewHolder(binding)
        }
    }

    fun bind(bankStatement: UiStatementModel) {
        binding.run {
            type.text = bankStatement.nameStatement
            description.text = bankStatement.description
            date.text = bankStatement.statementDate
            amount.text = bankStatement.amount
        }
    }
}
