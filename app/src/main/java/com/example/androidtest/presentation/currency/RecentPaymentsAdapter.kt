package com.example.androidtest.presentation.currency

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtest.R
import com.example.androidtest.repository.Payment
import kotlinx.android.synthetic.main.item_currency.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class RecentPaymentsAdapter(
    private val payments: List<Payment> = ArrayList(),
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecentPaymentVH>() {


    override fun getItemCount() = payments.size
    override fun getItemViewType(position: Int) =
        if (position == 0) R.layout.item_currency_payments_title
        else R.layout.item_currency

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPaymentVH {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return RecentPaymentVH(view)
    }


    override fun onBindViewHolder(viewHolder: RecentPaymentVH, position: Int) {
        if (position == 0) return
        val payment = payments[position]
        viewHolder.itemView.apply {
            txv_payment.text = payment.desc
            txv_value.text = payment.value.toCurrency()
            txv_date.text = payment.calendar.time.toSimpleString()
            setOnClickListener { onClickListener(position) }
        }
    }

}

private fun Date.toSimpleString(): String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)

private fun Double.toCurrency(): String {

    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)


//    var result = this.toString()
//
////    result.replace()
//
//    return "R$ $result"
}

class RecentPaymentVH(itemView: View) : RecyclerView.ViewHolder(itemView)
