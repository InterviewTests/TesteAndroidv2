package com.example.accentureprojectbank

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(val listaInf: MutableList<Informacoes>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.extrato_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listaInf.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val payment = holder.pagamento
        payment.text = listaInf[position].titulo

        val account = holder.conta
        account.text = listaInf[position].descricao

        val date = holder.data
        date.text = listaInf[position].data

        val value = holder.valor
        value.text = listaInf[position].valor.toString()
    }
}

