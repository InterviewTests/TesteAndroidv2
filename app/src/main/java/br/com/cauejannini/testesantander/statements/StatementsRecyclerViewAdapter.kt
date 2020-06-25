package br.com.cauejannini.testesantander.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cauejannini.testesantander.R
import br.com.cauejannini.testesantander.commons.Utils
import kotlinx.android.synthetic.main.list_item_statement.view.*

class StatementsRecyclerViewAdapter(val statementList: List<Statement>):
    RecyclerView.Adapter<StatementsRecyclerViewAdapter.StatementItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_statement, parent, false)
        return StatementItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statementList.size
    }

    override fun onBindViewHolder(holder: StatementItemViewHolder, position: Int) {
        val statement = statementList.get(position)
        holder.tvTitle.text = statement.title
        holder.tvDate.text = Utils.dataApiParaApp(statement.date)
        holder.tvDesc.text = statement.desc

        val value = Utils.doisDecimais(statement.value)
        holder.tvValue.text = "R$ $value"
    }

    class StatementItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.tvTitle
        val tvDesc = itemView.tvDesc
        val tvDate = itemView.tvDate
        val tvValue = itemView.tvValue

    }

}