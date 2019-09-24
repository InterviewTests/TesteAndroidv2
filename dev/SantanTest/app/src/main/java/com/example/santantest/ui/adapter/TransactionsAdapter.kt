package com.example.santantest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santantest.R
import com.example.santantest.domain.model.StatementItem
import com.example.santantest.domain.utils.AppUtils

class TransactionsAdapter(private val list: List<StatementItem>): RecyclerView.Adapter<TransactionsAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val type = itemView.findViewById<TextView>(R.id.tvTransactionType)
        val date = itemView.findViewById<TextView>(R.id.tvTransactionDate)
        val descr = itemView.findViewById<TextView>(R.id.tvTransactionDescr)
        val value = itemView.findViewById<TextView>(R.id.tvTransactionValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        list[position].let {
            holder.apply {
                type.text = it.title
                descr.text = it.desc
                date.text = it.date
                it.value?.let {
                    value.text = AppUtils.formatMoneyBr(it, true)
                }
            }
        }
    }
}