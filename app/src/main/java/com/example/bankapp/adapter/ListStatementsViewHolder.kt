package com.example.bankapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R
import com.example.bankapp.model.Statement

class ListStatementsViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_statemente, parent, false)) {

    private var mTxtTitle: TextView? = null
    private var mTxtDesc: TextView? = null
    private var mTxtData: TextView? = null
    private var mTxtValue: TextView? = null

    init {
        mTxtTitle = itemView.findViewById(R.id.txtTitle)
        mTxtDesc = itemView.findViewById(R.id.txtDesc)
        mTxtData = itemView.findViewById(R.id.txtData)
        mTxtValue = itemView.findViewById(R.id.txtValue)
    }

    fun bind(statement: Statement) {
        mTxtTitle!!.text = statement.title
        mTxtDesc!!.text = statement.desc
        mTxtData!!.text = statement.date
        mTxtValue!!.text = statement.value.toString()
    }
}