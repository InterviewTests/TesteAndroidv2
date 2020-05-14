package com.example.testeandroideveris.feature.statements.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testeandroideveris.R
import com.example.testeandroideveris.feature.statements.data.StatementData

class StatementListAdapter (private val list: List<StatementData>?)
    : RecyclerView.Adapter<StatementListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list != null) {
            val statement: StatementData = list[position]
            holder.bind(statement)
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.statements_item_layout, parent, false)) {
        private var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private var tvValue: TextView = itemView.findViewById(R.id.tvValue)

        fun bind(statement: StatementData) {
            tvTitle.text = statement.title
            tvDescription.text = statement.desc
            tvDate.text = statement.getFormattedDate()
            tvValue.text = statement.getFormattedValue()
        }
    }
}