package com.example.bankapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bankapp.R;
import com.example.bankapp.model.dashboard.statementList;

import java.util.List;

public class AdapterCurrency extends RecyclerView.Adapter<AdapterCurrency.MyViewHolder> {
    public List<statementList> list;

    public AdapterCurrency(List<statementList> statementLists) {
        this.list = statementLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_adapter_currency, viewGroup, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        statementList model = list.get(i);

        myViewHolder.textViewPayDay.setText(model.getDate());
        myViewHolder.textViewValue.setText(model.getValue() + "");
        myViewHolder.textViewTypeAccount.setText(model.getDesc());
        myViewHolder.textViewTitle.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPayDay;
        TextView textViewValue;
        TextView textViewTypeAccount;
        TextView textViewTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTypeAccount = itemView.findViewById(R.id.textViewTypeAccount);
            textViewValue = itemView.findViewById(R.id.textViewValue);
            textViewPayDay = itemView.findViewById(R.id.textViewPayDay);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
