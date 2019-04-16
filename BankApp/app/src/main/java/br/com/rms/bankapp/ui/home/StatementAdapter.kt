package br.com.rms.bankapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.utils.UtilsDate
import br.com.rms.bankapp.utils.UtilsMoneyFormatting
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_statement.*


class StatementAdapter : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    private val statements = mutableListOf<Statement>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(br.com.rms.bankapp.R.layout.item_statement, parent, false)
        return ViewHolder(containerView)
    }

    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statement = statements[position]

        holder.tvPaymentTitle.text = statement.title
        holder.tvStatementDate.text = statement.date?.let { UtilsDate.formatSimpleDate(it) }
        holder.tvStatementValue.text = statement.value?.let { UtilsMoneyFormatting.simpleMoneyFormmat(it) }
        holder.tvStatementDesc.text = statement.desc
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