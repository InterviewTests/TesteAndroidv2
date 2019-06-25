package com.example.santanderapplication.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.santanderapplication.R;

public class AdapterBankCurrency extends RecyclerView.Adapter<AdapterBankCurrency.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemList = LayoutInflater.from( parent.getContext() ).
                inflate( R.layout.card_adapter_bank_currency,parent,false );

        return new MyViewHolder( itemList );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
//        holder.dateConta.setText( "teste" );
//        holder.valueConta.setText( "0000" );
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView valueConta;
        TextView dateConta;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            valueConta = itemView.findViewById( R.id.conta_luz );
            dateConta=itemView.findViewById( R.id.date_pagamento);
        }
    }
}
