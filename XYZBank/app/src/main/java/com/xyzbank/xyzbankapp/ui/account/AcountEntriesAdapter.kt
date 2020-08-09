package com.xyzbank.xyzbankapp.ui.account

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.xyzbank.xyzbankapp.R
import com.xyzbank.xyzbankapp.data.model.AccountInfo
import java.util.*
import kotlin.collections.ArrayList

class AcountEntriesAdapter(val context: Context, val locale: Locale, val currency: Currency) : BaseAdapter() {
    private val mItems = ArrayList<AccountInfo>()
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var itemLayout: CardView? = null

    //private var DB: AnotationsDBHelper = AnotationsDBHelper(context)

    fun add(item: AccountInfo) {
        mItems.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        return mItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mItems.size
    }

    @SuppressLint("ViewHolder", "SetTextI18n")
    @Suppress("DEPRECATION")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position) as AccountInfo

        context.resources.displayMetrics

        val root: ViewGroup? = null
        itemLayout = inflater.inflate(R.layout.account_view, root) as CardView

        itemLayout!!.findViewById<TextView>(R.id.tv_title).text = item.title
        itemLayout!!.findViewById<TextView>(R.id.tv_date).text = item.date
        itemLayout!!.findViewById<TextView>(R.id.tv_desc).text = item.desc
        val tvValue = itemLayout!!.findViewById<TextView>(R.id.tv_value)
        val bal = Formatter(locale).format("%.2f", item.value)
        if (item.value < 0.00)
            tvValue.setTextColor(Color.parseColor("#A5A5A5"))
        tvValue.text = "${currency.symbol} $bal"


        return itemLayout as CardView
    }
}
