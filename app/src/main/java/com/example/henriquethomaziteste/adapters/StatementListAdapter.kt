package com.example.henriquethomaziteste.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.henriquethomaziteste.R
import com.example.henriquethomaziteste.apis.bankdata.BankStatement
import kotlinx.android.synthetic.main.item_balance_transaction.view.*

class StatementListAdapter(private val context: Context, private val items: ArrayList<BankStatement>) : RecyclerView.Adapter<StatementListAdapter.StatementHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatementListAdapter.StatementHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_balance_transaction, parent, false)
        return StatementHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: StatementListAdapter.StatementHolder, position: Int) {
        val item = items[position]

        holder.itemDate.setText(item.date)
        holder.itemDesc.setText(item.desc)
        holder.itemValue.setText("R$ ${item.value.toBigDecimal().setScale(2).toString().replace(".", ",")}")
        holder.itemType.setText(item.title)
    }

    inner class StatementHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemType = itemView.label_type
        var itemDesc = itemView.label_description
        var itemValue = itemView.label_valor
        var itemDate = itemView.label_date

    }

}