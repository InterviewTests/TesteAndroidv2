package br.com.rphmelo.bankapp.currency.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.rphmelo.bankapp.common.extensions.format
import br.com.rphmelo.bankapp.common.extensions.formatMoney
import br.com.rphmelo.bankapp.currency.domain.models.StatementModel
import kotlinx.android.synthetic.main.item_statement_list.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(itemList: StatementModel) {
        itemView.lblItemTitle?.text = itemList.title
        itemView.lblDescription?.text = itemList.desc
        itemView.lblDate?.text = itemList.date.format()
        itemView.lblValue?.text = itemList.value.formatMoney()
    }
}