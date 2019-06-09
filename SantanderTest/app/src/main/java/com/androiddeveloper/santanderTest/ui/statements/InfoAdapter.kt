package com.androiddeveloper.santanderTest.ui.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androiddeveloper.santanderTest.R
import com.androiddeveloper.santanderTest.data.model.statements.ItemDTO

class InfoAdapter : RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private var balanceList: ArrayList<ItemDTO>

    constructor(list: ArrayList<ItemDTO>) {
        this.balanceList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.bank_info_item, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return balanceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(balanceList[position])
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val itemType = v.findViewById<TextView>(R.id.tv_type_item)
        val itemDescription = v.findViewById<TextView>(R.id.tv_description_item)
        val itemDate = v.findViewById<TextView>(R.id.tv_date_item)
        val itemBalance = v.findViewById<TextView>(R.id.tv_balance_item)

        fun bind(item: ItemDTO) {
            itemType.text = item.title
            itemDescription.text = item.desc
            itemDate.text = item.date
            itemBalance.text = item.value
        }
    }
}