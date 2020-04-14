package pt.felipegouveia.bankapp.presentation.statements.adapter

import androidx.recyclerview.widget.DiffUtil
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementPresentation

class StatementDiffCallback(
    private val newStatementList: List<StatementPresentation>,
    private val oldStatementList: List<StatementPresentation>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return this.oldStatementList.size
    }

    override fun getNewListSize(): Int {
        return this.newStatementList.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldStatementList[oldPosition].title === newStatementList[newPosition].title
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldStatementList[oldPosition] == newStatementList[newPosition]
    }
}