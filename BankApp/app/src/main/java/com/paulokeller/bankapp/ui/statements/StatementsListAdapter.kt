package com.paulokeller.bankapp.ui.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paulokeller.bankapp.R
import com.paulokeller.bankapp.data.models.Statement
import com.paulokeller.bankapp.utils.Utils

class StatementsListAdapter(private val statementItemLayout: Int) : RecyclerView.Adapter<StatementsListAdapter.ViewHolder>() {
    private var statementList: List<Statement>? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = holder.title
        val desc = holder.desc
        val date = holder.date
        val value = holder.value
        statementList.let {
            title.text = it!![position].title
            desc.text = it[position].desc
            date.text = it[position].date
            value.text = Utils.formatCurrency(it[position].value.toFloat())
        }
    }

    fun setStatementList(products: List<Statement>) {
        statementList = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(statementItemLayout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (statementList == null) 0 else statementList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var desc: TextView = itemView.findViewById(R.id.desc)
        var date: TextView = itemView.findViewById(R.id.date)
        var value: TextView = itemView.findViewById(R.id.value)
    }
}