package com.accenture.primo.bankapp.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.accenture.primo.bankapp.R
import com.accenture.primo.bankapp.extension.formatDate
import com.accenture.primo.bankapp.extension.toMoney
import com.accenture.primo.bankapp.model.Statement
import com.accenture.primo.bankapp.ui.main.MainActivity

class StatementItemAdapter(private val activity: MainActivity): RecyclerView.Adapter<StatementItemAdapter.ViewHolder>(){

    override fun getItemCount(): Int = activity.getUserStatements().size

    override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(group.context).inflate(R.layout.statement_item, group, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(activity.getUserStatements()[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val lblTitle: TextView = itemView.findViewById(R.id.lblTitle)
        private val lblDescription: TextView = itemView.findViewById(R.id.lblDescription)
        private val lblDate: TextView = itemView.findViewById(R.id.lblDate)
        private val lblValue: TextView = itemView.findViewById(R.id.lblValue)

        fun bind(statement: Statement) {
            lblTitle.text = statement.title
            lblDescription.text = statement.desc
            lblDate.text = statement.date.formatDate()
            lblValue.text = statement.value.toMoney()
        }
    }
}