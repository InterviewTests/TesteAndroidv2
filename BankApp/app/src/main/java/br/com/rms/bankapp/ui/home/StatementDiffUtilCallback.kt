package br.com.rms.bankapp.ui.home

import androidx.recyclerview.widget.DiffUtil
import br.com.rms.bankapp.data.local.database.entity.Statement

class StatementDiffUtilCallback(
    private val oldStatementList: MutableList<Statement>,
    private val newSatementList: MutableList<Statement>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldStatementList.size

    override fun getNewListSize(): Int = newSatementList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (old,new) = getStatements(oldItemPosition,newItemPosition)
        return old.id == new.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (old,new) = getStatements(oldItemPosition,newItemPosition)
        return old.title.equals(new.title) && old.value == new.value
    }

    private fun getStatements(oldItemPosition: Int, newItemPosition: Int): Pair<Statement, Statement>{
        return Pair(
            oldStatementList[oldItemPosition],
            newSatementList[newItemPosition]
        )
    }
}
