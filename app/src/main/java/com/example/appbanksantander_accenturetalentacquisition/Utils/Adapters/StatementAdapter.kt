package com.example.appbanksantander_accenturetalentacquisition.Utils.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementModel
import com.example.appbanksantander_accenturetalentacquisition.R
import java.util.zip.Inflater

class StatementAdapter(var statement: List<StatementModel>, val context: Context): RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_stratement_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statements: StatementModel = statement.get(position)
        val cents = ",00"
        val currency = "R$ "
        holder.operationTitleTxt.text = statements.title
        holder.operationDateTxt.text = statements.date
        holder.descriptionTxt.text = statements.description
        holder.operationValueTxt.text = currency + statements.value.toString() + cents
    }

    override fun getItemCount(): Int {
        return statement.size
    }

    fun setList(statementList: List<StatementModel>){
        val moreList = ArrayList<StatementModel>()
        moreList.addAll(statementList)
        statement = moreList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val operationTitleTxt: TextView = itemView.findViewById(R.id.operationTitleTxt)
        val operationDateTxt: TextView = itemView.findViewById(R.id.operationDateTxt)
        val descriptionTxt: TextView = itemView.findViewById(R.id.descriptionTxt)
        val operationValueTxt: TextView = itemView.findViewById(R.id.operationValueTxt)
    }
}