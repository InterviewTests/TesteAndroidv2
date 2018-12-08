package br.com.rphmelo.bankapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.rphmelo.bankapp.models.StatementModel
import kotlinx.android.synthetic.main.item_statement_list.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(itemList: StatementModel) {
        val title = itemView.lblItemTitle
        val description = itemView.lblDescription
        val date = itemView.lblDate
        val value = itemView.lblValue

        title?.text = itemList.title
        description?.text = itemList.description
        date?.text = itemList.date
        value?.text = itemList.value.toString()
    }
}