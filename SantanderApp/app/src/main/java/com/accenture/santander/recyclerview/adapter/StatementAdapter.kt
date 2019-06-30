package com.accenture.santander.recyclerview.adapter

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import java.text.SimpleDateFormat
import java.util.*
import com.accenture.santander.R
import com.accenture.santander.databinding.ItemStatementBinding
import com.accenture.santander.databinding.ItemStatementHeaderBinding
import com.accenture.santander.recyclerview.viewholder.StatementHeaderViewHolder
import com.accenture.santander.recyclerview.viewholder.StatementViewHolder
import com.accenture.santander.viewmodel.Statement
import kotlin.collections.ArrayList


/**
 * Created by dev on 08/03/2018.
 */

class StatementAdapter(
    private val _itens: MutableList<Statement> = ArrayList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val HEADER_VIEW = 0
        private val TYPE_ITEM = 1
    }

    fun getStatements(): MutableList<Statement> {
        return _itens
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_ITEM) {
            val binding = DataBindingUtil.inflate<ItemStatementBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_statement,
                parent,
                false
            )
            return StatementViewHolder(binding)

        } else if (viewType == HEADER_VIEW) {
            val binding = DataBindingUtil.inflate<ItemStatementHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_statement_header,
                parent,
                false
            )
            return StatementHeaderViewHolder(binding)
        }

        throw RuntimeException("there is no type that matches the type $viewType + make sure your using types correctly")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StatementViewHolder) {
            holder.binding.statement = _itens[position - 1]
        }
    }

    override fun getItemCount(): Int {
        return _itens.size + 1
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) HEADER_VIEW else TYPE_ITEM
    }
}