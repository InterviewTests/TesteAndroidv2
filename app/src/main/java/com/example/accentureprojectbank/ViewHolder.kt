package com.example.accentureprojectbank

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val pagamento by lazy { view.findViewById<TextView>(R.id.tv_pagamento) }
    val conta by lazy { view.findViewById<TextView>(R.id.tv_conta_de_luz) }
    val data by lazy {view.findViewById<TextView>(R.id.tv_data)}
    val valor by lazy {view.findViewById<TextView>(R.id.tv_valor)}
    val cardView by lazy { view.findViewById<CardView>(R.id.card_view) }


}