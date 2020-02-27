package br.com.bank.android.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.bank.android.home.presentation.data.Stataments

class ListStatamanetsAdapter(private val stataments: List<Stataments>) :
    RecyclerView.Adapter<StatamentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatamentsViewHolder {
        return StatamentsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StatamentsViewHolder, position: Int) {
        holder.bind(stataments[position])
    }

    override fun getItemCount(): Int {
        return stataments.size
    }
}
