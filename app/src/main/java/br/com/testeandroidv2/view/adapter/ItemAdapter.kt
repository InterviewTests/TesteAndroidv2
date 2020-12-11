package br.com.testeandroidv2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import br.com.testeandroidv2.R
import br.com.testeandroidv2.model.statements.gson.StatementList
import br.com.testeandroidv2.utils.Utils

class ItemAdapter(private val list: MutableList<StatementList>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: StatementList = getItem(position)

        holder.apply {
            itemColumn1.text = item.title
            itemColumn2.text = item.desc
            itemColumn3.text = Utils.formatDate(item.date)
            itemColumn4.text = "R$" + Utils.formatDecimal(item.value)
        }
    }

    fun getItem(position: Int): StatementList = list[position]

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemColumn1: TextView = itemView.findViewById(R.id.itemColumn1)
        val itemColumn2: TextView = itemView.findViewById(R.id.itemColumn2)
        val itemColumn3: TextView = itemView.findViewById(R.id.itemColumn3)
        val itemColumn4: TextView = itemView.findViewById(R.id.itemColumn4)
    }
}