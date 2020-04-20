package com.jfgjunior.bankapp.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jfgjunior.bankapp.R
import com.jfgjunior.bankapp.data.models.Statement
import com.jfgjunior.bankapp.setValueAsCurrency
import kotlinx.android.synthetic.main.item_transaction.view.*

class StatementAdapter :
    ListAdapter<Statement, StatementAdapter.StatementViewHolder>(statementDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return StatementViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(statement: Statement) {
            itemView.title.text = statement.title
            itemView.date.text = statement.formattedDate
            itemView.description.text = statement.description
            itemView.value.setValueAsCurrency(statement.value)
        }
    }

    companion object {

        private val statementDiff = object : DiffUtil.ItemCallback<Statement>() {
            override fun areItemsTheSame(oldItem: Statement, newItem: Statement) =
                areContentsTheSame(oldItem, newItem)

            override fun areContentsTheSame(oldItem: Statement, newItem: Statement) =
                oldItem == newItem
        }
    }
}