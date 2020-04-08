package com.example.testeandroidv2.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeandroidv2.R
import com.example.testeandroidv2.model.Statement
import com.example.testeandroidv2.util.currency
import com.example.testeandroidv2.util.dateFormat
import kotlinx.android.synthetic.main.item.view.*

class StatementsAdapter (
    val statements: List<Statement>
) : RecyclerView.Adapter<StatementsAdapter.StatementsViewHolder>() {

    class StatementsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.textTitle
        private val value = itemView.textValue
        private val date = itemView.textDate
        private val description = itemView.textDescription
        fun bindView(statment: Statement){
            title.text = statment.title
            value.text = currency(statment.value)
            date.text = dateFormat(statment.date)
            description.text = statment.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return StatementsViewHolder(view)
    }

    override fun getItemCount() = statements.count()

    override fun onBindViewHolder(holder: StatementsViewHolder, position: Int) {
        holder.bindView(statements[position])
    }
}