package br.com.santander.android.bank.statement.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.santander.android.bank.R
import br.com.santander.android.bank.core.extensions.format
import br.com.santander.android.bank.core.extensions.toCurrency
import br.com.santander.android.bank.statement.domain.model.Statement

internal class StatementAdapter(private val statements: List<Statement>)
    : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_statement, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = statements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            title.text = statements[position].title
            description.text = statements[position].description
            date.text = statements[position].date.format()
            value.text = statements[position].value.toCurrency()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.txt_statement_title)
        var description: TextView = view.findViewById(R.id.txt_statement_desc)
        var date: TextView = view.findViewById(R.id.txt_statement_date)
        var value: TextView = view.findViewById(R.id.txt_statement_value)
    }

}