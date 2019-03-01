package com.example.androidtest.presentation.currency

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtest.R
import com.example.androidtest.repository.Statement
import com.example.androidtest.util.toCurrency
import com.example.androidtest.util.toSimpleString
import kotlinx.android.synthetic.main.item_currency.view.*
import java.util.*

class RecentPaymentsAdapter(
    private val statements: List<Statement> = ArrayList(),
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecentPaymentVH>() {


    override fun getItemCount() = statements.size
    override fun getItemViewType(position: Int) =
        if (position == 0) R.layout.item_currency_payments_title
        else R.layout.item_currency

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPaymentVH {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return RecentPaymentVH(view)
    }


    override fun onBindViewHolder(viewHolder: RecentPaymentVH, position: Int) {
        if (position == 0) return
        val payment = statements[position]
        viewHolder.itemView.apply {
            txv_payment.text = payment.desc
            txv_value.text = payment.value.toCurrency()
            txv_date.text = payment.calendar.time.toSimpleString()
            setOnClickListener { onClickListener(position) }
        }
    }

}


class RecentPaymentVH(itemView: View) : RecyclerView.ViewHolder(itemView)
