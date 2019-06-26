package com.example.desafiosantander.feature.summary.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiosantander.data.model.basic.Statement
import com.example.desafiosantander.extensions.formatDate
import com.example.desafiosantander.extensions.formatDoubleToMoney
import kotlinx.android.synthetic.main.item_statement.view.date
import kotlinx.android.synthetic.main.item_statement.view.desc
import kotlinx.android.synthetic.main.item_statement.view.title
import kotlinx.android.synthetic.main.item_statement.view.value

class SummaryViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

    fun bind(statement: Statement) {
        item.title.text = statement.title
        item.desc.text = statement.desc
        item.date.text = statement.date.formatDate()
        item.value.text = statement.value.formatDoubleToMoney()
    }

}