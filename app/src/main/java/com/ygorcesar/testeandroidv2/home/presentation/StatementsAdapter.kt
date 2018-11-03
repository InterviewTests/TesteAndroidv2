package com.ygorcesar.testeandroidv2.home.presentation

import com.ygorcesar.testeandroidv2.R
import com.ygorcesar.testeandroidv2.base.extensions.dateToFormatBr
import com.ygorcesar.testeandroidv2.base.extensions.formatCurrency
import com.ygorcesar.testeandroidv2.base.presentation.BaseRecyclerViewAdapter
import com.ygorcesar.testeandroidv2.home.model.Statement
import kotlinx.android.synthetic.main.home_statement_item.view.*

class StatementsAdapter(statements: List<Statement>) : BaseRecyclerViewAdapter<Statement>(
    items = statements.toMutableList(),
    layoutResId = R.layout.home_statement_item,
    bindView = { view, item ->
        view.homeStatementItemTitle?.text = item.title
        view.homeStatementItemDesc?.text = item.desc
        view.homeStatementItemDate?.text = item.date.dateToFormatBr()
        view.homeStatementItemValue?.text = item.value.formatCurrency()
    }
)