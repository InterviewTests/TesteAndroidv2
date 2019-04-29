package br.com.alex.bankappchallenge.feature.statement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.bankappchallenge.R
import br.com.alex.bankappchallenge.model.FormatedStatement
import kotlinx.android.synthetic.main.item_statement.view.*
import kotlin.properties.Delegates

class StatementAdapter : RecyclerView.Adapter<StatementViewHolder>() {

    var statementList by Delegates.observable(emptyList<FormatedStatement>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StatementViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_statement, parent, false)
        )

    override fun getItemCount() = statementList.size

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        holder.bind(statementList[position])
    }
}

class StatementViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(formatedStatement: FormatedStatement) {
        view.textViewTitle.text = formatedStatement.title
        view.textViewDescription.text = formatedStatement.description
        view.textViewDate.text = formatedStatement.date
        view.textViewValue.text = formatedStatement.value
    }
}