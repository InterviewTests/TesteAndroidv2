package com.tata.bank.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tata.bank.R
import kotlinx.android.synthetic.main.item_statement.view.*

class MainAdapter(private var statements: List<Statement>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title = view.tv_title
        private val description = view.tv_description
        private val value = view.tv_value
        private val date = view.tv_date

        fun bind(statement: Statement) {
            title.text = statement.title
            description.text = statement.desc
            value.text = statement.value.toString()
            date.text = statement.date
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
    )

    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(statements[position])

    fun updateItems(newStatements: List<Statement>) {
        this.statements = newStatements
        this.notifyDataSetChanged()
    }
}