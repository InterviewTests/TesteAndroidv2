package com.example.androidtest.presentation.home

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.androidtest.R
import com.example.androidtest.domain.entities.StatementsEntity
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StatementAdapter(private val context: Context,
                       private val listModel: List<StatementsEntity>) : BaseAdapter() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_statement, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        val formatDate = LocalDate.parse(listModel[position].date)
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = formatDate.format(formatter)
        val dec = DecimalFormat("#,###.00")

        vh.text1.text = listModel[position].title
        vh.text2.text = date.toString()
        vh.text3.text = listModel[position].desc
        vh.text4.text = "R$ " + dec.format(listModel[position].value)

        if (listModel[position].value < 0) {
            vh.text4.setTextColor(Color.parseColor("#ec1c24"))
        } else {
            vh.text4.setTextColor(Color.parseColor("#454545"))
        }

        return view!!
    }

    override fun getItem(position: Int): Any {
        return listModel[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listModel.size
    }

    private class ViewHolder(view: View?) {
        val text1: TextView = view?.findViewById(R.id.text1) as TextView
        val text2: TextView = view?.findViewById(R.id.text2) as TextView
        val text3: TextView = view?.findViewById(R.id.text3) as TextView
        val text4: TextView = view?.findViewById(R.id.text4) as TextView

    }
}