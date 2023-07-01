package com.nandoligeiro.safrando.presentation.statements.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nandoligeiro.safrando.presentation.statements.model.UiStatementModel

class BankStatementsAdapter :
    ListAdapter<UiStatementModel, BankStatementItemViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiStatementModel>() {
            override fun areItemsTheSame(
                oldItem: UiStatementModel, newItem: UiStatementModel
            ) = oldItem.nameStatement == newItem.nameStatement

            override fun areContentsTheSame(
                oldItem: UiStatementModel, newItem: UiStatementModel
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = BankStatementItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: BankStatementItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
