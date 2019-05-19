package br.com.teste.santander.statements.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.teste.santander.R
import br.com.teste.santander.model.Statement
import kotlinx.android.synthetic.main.statements_item.view.*

class StatementsListAdapter(private val context: Context, private val itens: List<Statement>) : Adapter<StatementsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.statements_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statement = itens[position]

        holder.itemView.txtTitle.text = statement.title
        holder.itemView.txtDate.text = statement.date
        holder.itemView.txtDescription.text = statement.desc
        holder.itemView.txtValue.text = String.format(context.getString(R.string.balance, statement.value))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}