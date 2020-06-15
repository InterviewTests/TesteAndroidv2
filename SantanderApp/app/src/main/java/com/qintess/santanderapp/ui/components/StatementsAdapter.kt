package com.qintess.santanderapp.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.qintess.santanderapp.R
import com.qintess.santanderapp.helper.Formatter
import com.qintess.santanderapp.model.StatementModel
import kotlinx.android.synthetic.main.list_item_statement.view.*

class StatementsAdapter(ctx: Context, private val statements: ArrayList<StatementModel>): BaseAdapter() {
    private val inflater: LayoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_statement, parent, false)
            holder = ViewHolder()
            holder.type = view.type
            holder.date = view.date
            holder.description = view.description
            holder.value = view.value
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }


        val statement = getItem(position)
        holder.type?.text = statement.type.value
        holder.date?.text = Formatter.formatDate(statement.date)
        holder.description?.text = statement.desc
        holder.value?.text = Formatter.formatMoney(statement.value)

        return view
    }

    override fun getItem(position: Int): StatementModel {
        return statements[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return statements.size
    }

    class ViewHolder {
        var type: TextView? = null
        var date: TextView? = null
        var description: TextView? = null
        var value: TextView? = null
    }

}
