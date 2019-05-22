package com.felipemsa.idletime.ui.statements.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.felipemsa.idletime.data.Statement
import com.felipemsa.idletime.helper.formatToCurrency
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_transaction.*

class StatementViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    fun bind(statement: Statement) {
        tv_title.text = statement.title
        tv_date.text = statement.date
        tv_description.text = statement.desc
        tv_value.text = statement.value.formatToCurrency()
    }

}