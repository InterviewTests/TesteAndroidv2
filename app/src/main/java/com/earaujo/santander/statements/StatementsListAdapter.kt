package com.earaujo.santander.statements

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earaujo.santander.R
import com.earaujo.santander.repository.models.StatementsListModel
import kotlinx.android.synthetic.main.item_statements_card.view.*

class StatementsListAdapter(private val statements: List<StatementsListModel>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return getItemViewHolder(parent, inflater)
    }

    private fun getItemViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_statements_card, parent, false)
        inflater.inflate(R.layout.item_statements_card, parent, false)
        return StatementViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StatementViewHolder).apply {
            bind(statements[layoutPosition])
        }
    }

    override fun getItemCount(): Int {
        return statements.size
    }

    inner class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(statement: StatementsListModel) {
            itemView.tv_title.text = statement.title
            itemView.tv_date.text = statement.date
            itemView.tv_description.text = statement.desc
            itemView.tv_value.text = statement.value.toString()
        }

    }

}