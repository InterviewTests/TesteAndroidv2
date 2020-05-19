package br.com.crmm.bankapplication.framework.presentation.ui.statement

import androidx.recyclerview.widget.RecyclerView
import br.com.crmm.bankapplication.databinding.StatementViewHolderLayoutBinding
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.StatementDataResponseDTO

class StatementViewHolder(
    private val itemBinding: StatementViewHolderLayoutBinding
): RecyclerView.ViewHolder(itemBinding.root){

    fun setItem(presentation: StatementDataResponseDTO){
        itemBinding.presentation = presentation
        itemBinding.executePendingBindings()
    }
}