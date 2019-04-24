package com.example.testeandroidsantander.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.testeandroidsantander.R
import com.example.testeandroidsantander.model.Statement
import com.example.testeandroidsantander.model.StatementList
import kotlinx.android.synthetic.main.bank_statement_item.view.*
import java.text.DecimalFormat


class StatementAdapter(private val statement: StatementList,
                       private val context: Context
) : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return statement.statementList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = statement.statementList[position]
        holder.bindView(note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bank_statement_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recentLabel: TextView
        val typeTextView: TextView
        val dateLabel: TextView
        val valueTextView: TextView
        var item: Statement? = null

        init {
            recentLabel = itemView.recentLabel
            typeTextView = itemView.typeTextView
            dateLabel = itemView.dateLabel
            valueTextView = itemView.valueTextView
        }

        fun bindView(item: Statement) {
            this.item = item

            val recentLabel = itemView.recentLabel
            val typeTextView = itemView.typeTextView
            val dateLabel = itemView.dateLabel
            val valueTextView = itemView.valueTextView

            recentLabel.text = item.title
            typeTextView.text = item.desc
            dateLabel.text = item.date
            valueTextView.text = item.value
        }
    }
}