package br.com.bankapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.bankapp.R
import br.com.bankapp.domain.models.Statement
import br.com.bankapp.utils.brazilianDateFormat
import br.com.bankapp.utils.brazilianFormat
import kotlinx.android.synthetic.main.layout_statement_item.view.*

class StatementsListAdapter
    : PagedListAdapter<Statement, StatementsListAdapter.StatementsViewHolder>(STATEMENT_COMPARATOR) {

    class StatementsViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        var paymentView: TextView?= null
        var dateView: TextView?= null
        var discriminationView: TextView?= null
        var valueView: TextView?= null

        init {
            paymentView = mView.payment_text
            dateView = mView.date_text
            discriminationView = mView.discrimination_text
            valueView = mView.value_text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_statement_item, parent, false)
        return StatementsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatementsViewHolder, position: Int) {
        val item = getItem(position)

        holder.paymentView!!.text = item!!.title
        holder.dateView!!.text = brazilianDateFormat(item.date)
        holder.discriminationView!!.text = item.desc
        holder.valueView!!.text = brazilianFormat(item.value)
    }

    companion object {
        private val STATEMENT_COMPARATOR = object : DiffUtil.ItemCallback<Statement>() {
            override fun areItemsTheSame(oldItem: Statement, newItem: Statement): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Statement, newItem: Statement): Boolean =
                oldItem == newItem
        }
    }
}