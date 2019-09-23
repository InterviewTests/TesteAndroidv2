package com.gustavo.bankandroid.statements.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.bankandroid.entity.UserStatementItem

class StatementAdapter() : RecyclerView.Adapter<StatementViewHolder>() {

    var statementList: List<UserStatementItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder =
        StatementViewHolder(parent)

    override fun getItemCount(): Int = statementList.size

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        holder.bind(statementList[position])
    }
}