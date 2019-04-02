package br.com.rms.bankapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ScrollingTabContainerView
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.bankapp.R
import br.com.rms.bankapp.data.local.database.entity.Statement
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_statement.*


class StatementAdapter : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    private val statements = mutableListOf<Statement>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_statement,parent, false)
        return ViewHolder(containerView)
    }

    override fun getItemCount(): Int = statements.size

    override fun onBindViewHolder(holder: StatementAdapter.ViewHolder, position: Int) {
        val statement = statements[position]
        holder.tvPaymentTitle.text = statement.title
        holder.tvStatementDate.text = statement.date
        holder.tvStatementValue.text = statement.value.toString()
        holder.tvStatementDesc.text = statement.desc
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer


}