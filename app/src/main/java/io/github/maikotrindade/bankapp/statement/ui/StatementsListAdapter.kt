package io.github.maikotrindade.bankapp.statement.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.base.util.StringsUtil
import io.github.maikotrindade.bankapp.base.util.StringsUtil.formatDate
import io.github.maikotrindade.bankapp.statement.model.Statement


class StatementsListAdapter(var statements: List<Statement>) : RecyclerView.Adapter<StatementsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootViews = LayoutInflater.from(parent.context).inflate(R.layout.statement_item, parent, false)
        return ViewHolder(rootViews)
    }

    override fun getItemCount(): Int {
        return statements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statement = statements[position]
        holder.txtTitle.text = statement.title
        holder.txtDescription.text = statement.desc
        holder.txtDate.text = formatDate(statement.date)
        holder.txtBalance.text =  StringsUtil.convertDoubleToCurrency(statement.value)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtDescription: TextView = view.findViewById(R.id.txtDescription)
        val txtDate: TextView = view.findViewById(R.id.txtDate)
        val txtBalance: TextView = view.findViewById(R.id.txtBalance)
    }
}