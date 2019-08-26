package com.valber.santandertestvalber.presentation


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.valber.data.model.Statement
import com.valber.data.model.showDate
import com.valber.data.model.showValue
import com.valber.santandertestvalber.R
import com.valber.santandertestvalber.extensions.inflate
import kotlinx.android.synthetic.main.layout_description_conta.view.*

class StatementAdapter : ListAdapter<Statement, StatementAdapter.MyHolder>(DiffCAll()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder = MyHolder(parent)

    override fun onBindViewHolder(holder: MyHolder, position: Int)  = holder.bind(getItem(position))

    inner class MyHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.layout_description_conta)) {
        fun bind(item: Statement) {
            itemView.title_desc_text.text = item.title
            itemView.data_desc_text.text = item.showDate()
            itemView.desc_desc_text.text = item.desc
            itemView.value_desc_text.text = item.showValue()
        }
    }

}

private class DiffCAll : DiffUtil.ItemCallback<Statement>() {
    override fun areItemsTheSame(oldItem: Statement, newItem: Statement): Boolean {
        return oldItem.date == newItem.date && oldItem.desc == newItem.desc && oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Statement, newItem: Statement): Boolean {
        return oldItem == newItem
    }

}
