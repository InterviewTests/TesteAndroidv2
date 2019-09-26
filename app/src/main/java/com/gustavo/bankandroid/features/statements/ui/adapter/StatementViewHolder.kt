package com.gustavo.bankandroid.features.statements.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.bankandroid.R
import com.gustavo.bankandroid.common.extensions.inflate
import com.gustavo.bankandroid.common.extensions.toDisplayDateFormat
import com.gustavo.bankandroid.common.extensions.toRealCurrency
import com.gustavo.bankandroid.entity.UserStatementItem
import kotlinx.android.synthetic.main.item_statement.view.*

class StatementViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.item_statement)) {

    fun bind(statement: UserStatementItem) {
        itemView.titleTextView.text = statement.title
        itemView.descTextView.text = statement.desc
        itemView.dateTextView.text = statement.date.toDisplayDateFormat()
        itemView.valueTextView.text = statement.value.toRealCurrency()
    }

}