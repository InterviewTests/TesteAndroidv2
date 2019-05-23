package com.felipemsa.idletime.ui.statements.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.felipemsa.idletime.R
import com.felipemsa.idletime.data.Statement
import com.felipemsa.idletime.formatToCurrency
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_transaction.*
import org.joda.time.DateTime

class StatementViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(statement: Statement) {
        with(statement) {
            tv_title.text = title
            tv_date.text = DateTime(date.time).toString("dd/MM/yyyy")
            tv_description.text = desc
            tv_value.text = value.formatToCurrency()

            if (value >= 0)
                tv_value.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPositiveBalance))
            else
                tv_value.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorNegativeBalance))
        }

    }

}