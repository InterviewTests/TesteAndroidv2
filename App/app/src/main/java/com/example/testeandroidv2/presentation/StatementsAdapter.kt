package com.example.testeandroidv2.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeandroidv2.R
import com.example.testeandroidv2.model.Statement
import kotlinx.android.synthetic.main.item.view.*

class StatementsAdapter (
    val statements: List<Statement>
) : RecyclerView.Adapter<StatementsAdapter.BooksViewHolder>() {

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.textTitle
        private val value = itemView.textValue
        fun bindView(book: Statement){
            title.text = book.title
            value.text = book.value.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount() = statements.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(statements[position])
    }
}