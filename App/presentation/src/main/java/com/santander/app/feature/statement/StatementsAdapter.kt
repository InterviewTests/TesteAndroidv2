package com.santander.app.feature.statement

import android.view.View
import com.santander.app.R
import com.santander.app.core.ui.base.adapter.BaseAdapter
import com.santander.app.core.ui.base.adapter.BaseViewHolder
import com.santander.app.core.util.extension.formatted
import com.santander.app.core.util.extension.onClick
import com.santander.app.core.util.extension.toDate
import com.santander.domain.entity.business.Statement
import kotlinx.android.synthetic.main.item_statement.view.*


class StatementsAdapter(list: MutableList<Statement> = mutableListOf(), private val listener: OnItemClickListener? = null) :
    BaseAdapter<Statement, StatementsAdapter.ViewHolder>(list) {

    override fun getItemView() = R.layout.item_statement

    override fun instantiateViewHolder(view: View): ViewHolder = ViewHolder(view, listener)

    inner class ViewHolder(private val view: View, private val listener: OnItemClickListener? = null) : BaseViewHolder<Statement>(view) {

        override fun onBind(item: Statement, position: Int, isLastItem: Boolean) {
            with(view) {
                tvTitle?.text = item.title
                tvDate?.text = item.date.toDate()?.formatted()
                tvDescription?.text = item.desc
                tvValue?.text = item.value.formatted()
                containerView?.onClick {
                    listener?.onStatementClicked(item)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onStatementClicked(item: Statement)
    }

}