package br.com.learncleanarchitecture.home.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import br.com.learncleanarchitecture.R
import java.lang.Exception


class HomeAdapter(var context: FragmentActivity, var listStatment: List<Statment>) : BaseAdapter() {

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater
            .from(context) as LayoutInflater

        val convertView = inflater.inflate(R.layout.list_item_statment, parent, false)

        val tvTitle = convertView.findViewById<TextView>(R.id.tv_title)
        val tvDesc = convertView.findViewById<TextView>(R.id.tv_desc)
        val tvDate = convertView.findViewById<TextView>(R.id.tv_date)
        val tvValue = convertView.findViewById<TextView>(R.id.tv_value)

        tvTitle.text = getItem(position).title
        tvDesc.text = getItem(position).desc
        tvDate.text = getDateFormat(position)
        tvValue.text = "R$${getItem(position).value.toString().replace(".", ",")}"

        return convertView
    }

    private fun getDateFormat(position: Int): String {
        val date = getItem(position).date
        if(date.length > 8 ) {
            val ano = date.substring(0, 4)
            val mes = date.substring(5, 7)
            val dia = date.substring(date.length - 2, date.length)

            return "$dia/$mes/$ano"
        }

        return ""
    }

    override fun getItem(position: Int): Statment {
        return listStatment[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listStatment.size
    }
}