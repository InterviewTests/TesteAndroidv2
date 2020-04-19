package br.com.rcp.bank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rcp.bank.R
import br.com.rcp.bank.databinding.ItemStatementBinding
import br.com.rcp.bank.dto.StatementDTO

class StatementsAdapter: RecyclerView.Adapter<StatementsAdapter.ItemViewHolder>(), AdapterBinder<MutableList<StatementDTO>> {
	private		val		collection		= mutableListOf<StatementDTO>()

	override fun onCreateViewHolder(parent: ViewGroup, type: Int): ItemViewHolder {
		return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_statement, parent, false))
	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
		holder.binding.model 	= collection[position]
		holder.binding.executePendingBindings()
	}

	override fun getItemCount(): Int {
		return collection.size
	}

	override fun setData(data: MutableList<StatementDTO>) {
		val result = DiffUtil.calculateDiff(object: DiffUtil.Callback() {
			override fun	getOldListSize() 						= collection.size
			override fun	getNewListSize() 						= data.size
			override fun 	areItemsTheSame(old: Int, new: Int) 	= false
			override fun 	areContentsTheSame(old: Int, new: Int)	= false
		})

		collection.clear()
		collection.addAll(data)
		result.dispatchUpdatesTo(this)
	}

	class ItemViewHolder(val binding: ItemStatementBinding): RecyclerView.ViewHolder(binding.root)
}

interface AdapterBinder<T> {
	fun setData(data: T)
}