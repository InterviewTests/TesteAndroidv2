package com.example.appbank.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appbank.R;

public class AdapterAccount extends RecyclerView.Adapter<AdapterAccount.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemList = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_account_adapter, viewGroup, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textViewPagamento.setText("Pagamento");
        myViewHolder.textViewDate.setText("12/12/2018");
        myViewHolder.textViewDesc.setText("Conta de Luz");
        myViewHolder.textViewValue.setText("R$ 1.000,00");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPagamento;
        private TextView textViewDate;
        private TextView textViewDesc;
        private TextView textViewValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPagamento = itemView.findViewById(R.id.textViewPagamento);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewValue = itemView.findViewById(R.id.textViewValue);
        }
    }
}
