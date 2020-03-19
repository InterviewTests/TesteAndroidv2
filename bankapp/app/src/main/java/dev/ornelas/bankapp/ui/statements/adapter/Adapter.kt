package dev.ornelas.bankapp.ui.statements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dev.ornelas.bankapp.R
import dev.ornelas.bankapp.ui.statements.StatementViewModel

class StatementsAdapter : RecyclerView.Adapter<ViewHolder>() {

    lateinit var statementsViewModel: List<StatementViewModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_content_statement, parent, false) as MaterialCardView
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statementsViewModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            populateViews(statementsViewModel[position])
        }
    }

    fun updateAdapter(statementsViewModel: List<StatementViewModel>) {
        this.statementsViewModel = statementsViewModel
        notifyDataSetChanged()
    }

}