package com.lucianogiardino.bankapp.ui.statement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucianogiardino.bankapp.R
import com.lucianogiardino.bankapp.domain.model.Statement
import kotlinx.android.synthetic.main.statement_item.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class StatementAdapter(
    private val statementList: ArrayList<Statement>,
    private val context: Context
) : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statement = statementList[position]
        holder?.let {
            it.bindView(statement)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.statement_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statementList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(statement: Statement) {
            val title = itemView.tvTitle
            val desc = itemView.tvDesc
            val date = itemView.tvDate
            val value = itemView.tvValue

            var formatCurrency: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            var valueFormated = formatCurrency.format(statement.value)

            val formatDate = SimpleDateFormat("dd/MM/yyyy")
            val convertedDate = SimpleDateFormat("yyyy-MM-dd").parse(statement.date)
            title.text = statement.title
            desc.text = statement.desc
            date.text = formatDate.format(convertedDate)
            value.text = valueFormated
        }
    }


}