package com.example.testeandroidv2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testeandroidv2.R
import com.example.testeandroidv2.model.statement.StatementItem
import kotlinx.android.synthetic.main.statement_list_item.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat

class StatementListAdapter(private val context: Context, private val statementList: ArrayList<StatementItem>) :
    RecyclerView.Adapter<StatementListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.statement_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return statementList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = statementList[position]

        holder.title.text = item.title
        holder.date.text = SimpleDateFormat("dd/MM/yyyy").format(item.date)
        holder.description.text = item.desc
        holder.value.text = NumberFormat.getCurrencyInstance().format(item.value)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.statement_item_title
        val date: TextView = itemView.statement_item_date
        val description: TextView = itemView.statement_item_description
        val value: TextView = itemView.statement_item_value
    }
}