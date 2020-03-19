package dev.ornelas.bankapp.ui.statements.adapter

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import dev.ornelas.bankapp.ui.statements.StatementViewModel
import kotlinx.android.synthetic.main.item_content_statement.view.*

class ViewHolder(itemView: MaterialCardView) : RecyclerView.ViewHolder(itemView) {

    fun populateViews(statementViewModel: StatementViewModel) {

        itemView.statementTitle.text = statementViewModel.title
        itemView.statementDesc.text = statementViewModel.desc
        itemView.statementDate.text = statementViewModel.date
        itemView.statementValue.text = statementViewModel.value
    }
}
