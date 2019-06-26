package com.example.desafiosantander.feature.summary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiosantander.R
import com.example.desafiosantander.data.model.basic.Statement

class SummaryAdapter(private val statements: List<Statement>) : RecyclerView.Adapter<SummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        return SummaryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_statement, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        holder.bind(statements[position])
    }

    override fun getItemCount() = statements.size
}