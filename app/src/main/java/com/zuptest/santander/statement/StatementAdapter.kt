package com.zuptest.santander.statement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zuptest.santander.R
import com.zuptest.santander.domain.business.model.Statement
import com.zuptest.santander.domain.formattedPrint
import org.jetbrains.anko.find

class StatementAdapter(private val statements: List<Statement>) : RecyclerView.Adapter<StatementVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
        return StatementVH(view)
    }

    override fun getItemCount() = statements.size

    override fun onBindViewHolder(holder: StatementVH, position: Int) {
        holder.attach(statements[position])
    }
}

class StatementVH(view: View) : RecyclerView.ViewHolder(view) {
    fun attach(statement: Statement) {
        itemView.let {
            it.find<TextView>(R.id.title).text = statement.title
            it.find<TextView>(R.id.description).text = statement.description
            it.find<TextView>(R.id.date).text = statement.date.formattedPrint()
            it.find<TextView>(R.id.amount).text = statement.amount.formattedPrint()
        }

    }

}
