package com.example.bankapp.ui.dashboard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bankapp.R;
import com.example.bankapp.data.remote.model.dashboard.StatementList;
import com.example.bankapp.helper.MyConveter;

import java.util.List;

public class AdapterCurrency extends RecyclerView.Adapter<AdapterCurrency.MyViewHolder> {
    public List<StatementList> list;

    public AdapterCurrency(List<StatementList> statementLists) {
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
        StatementList model = list.get(i);

        myViewHolder.textViewPayDay.setText(MyConveter.formatDate(model.getDate()));
        myViewHolder.textViewValue.setText(MyConveter.formatCurrency(model.getValue())
                .replace("(", "-")
                .replace(")", ""));
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
