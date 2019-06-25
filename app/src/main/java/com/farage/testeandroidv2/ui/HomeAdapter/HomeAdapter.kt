package com.farage.testeandroidv2.ui.HomeAdapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.farage.testeandroidv2.R
import com.farage.testeandroidv2.domain.model.StatementsModel

class HomeAdapter(private val statements: List<StatementsModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_statement, parent, false)
        )

    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            val statement = statements[position]
            title.text = statement.title
            desc.text = statement.desc
            date.text = statement.date
            value.text = statement.value
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.item_statement_title)
        val desc: TextView = view.findViewById(R.id.item_statement_desc)
        val date: TextView = view.findViewById(R.id.item_statement_date)
        val value: TextView = view.findViewById(R.id.item_statement_value)
    }

}