package com.jeanjnap.bankapp.ui.statements

import com.jeanjnap.bankapp.R
import com.jeanjnap.bankapp.databinding.ItemStatementBinding
import com.jeanjnap.bankapp.ui.base.BaseAdapter
import com.jeanjnap.bankapp.util.extension.adapterDataBindingCast
import com.jeanjnap.domain.entity.Statement

class StatementsAdapter : BaseAdapter<Statement>(R.layout.item_statement, { statement, view ->
    with(view.adapterDataBindingCast<ItemStatementBinding>()) {
        this.statement = statement
    }
})