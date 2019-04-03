package br.com.rms.bankapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.bankapp.data.local.database.entity.Statement
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_statement.*
import java.text.NumberFormat
import java.text.SimpleDateFormat


class StatementAdapter : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    private val statements = mutableListOf<Statement>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(br.com.rms.bankapp.R.layout.item_statement, parent, false)
        return ViewHolder(containerView)
    }

    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(holder: StatementAdapter.ViewHolder, position: Int) {
        val statement = statements[position]

        holder.tvPaymentTitle.text = statement.title
        formatStatementDate(holder, statement)
        formatStatementValue(holder, statement)
        holder.tvStatementDesc.text = statement.desc
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatStatementDate(holder: ViewHolder, statement: Statement) {
        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val output = formatter.format(parser.parse(statement.date))
        holder.tvStatementDate.text = output
    }

    private fun formatStatementValue(holder: ViewHolder, statement: Statement) {
        val numberFormat = NumberFormat.getCurrencyInstance()
        holder.tvStatementValue.text = numberFormat.format(statement.value)
    }


    fun addStatements(newStatements: List<Statement>) {
        val oldStatementList = mutableListOf<Statement>()
        oldStatementList.addAll(statements)
        statements.addAll(newStatements)
        val statementDiffUtilCallback = StatementDiffUtilCallback(oldStatementList, statements)
        DiffUtil.calculateDiff(statementDiffUtilCallback).dispatchUpdatesTo(this)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer


}