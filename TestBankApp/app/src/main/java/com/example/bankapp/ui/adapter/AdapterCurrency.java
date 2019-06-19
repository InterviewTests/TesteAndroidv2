package com.example.bankapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bankapp.R;
import com.example.bankapp.UserDataAccount;

import java.util.List;

public class AdapterCurrency extends RecyclerView.Adapter<AdapterCurrency.MyViewHolder>{
    public List<UserDataAccount> userDataAccountList;

    public AdapterCurrency(List<UserDataAccount> userDataAccountList) {
        this.userDataAccountList = userDataAccountList;
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
        UserDataAccount userDataAccount = userDataAccountList.get(i);

        myViewHolder.textViewPayDay.setText(userDataAccount.getDate());
        myViewHolder.textViewValue.setText(userDataAccount.getValue()+"");
        myViewHolder.textViewTypeAccount.setText(userDataAccount.getTypeAccount());
    }

    @Override
    public int getItemCount() {
        return userDataAccountList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewPayDay;
        TextView textViewValue;
        TextView textViewTypeAccount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTypeAccount = itemView.findViewById(R.id.textViewTypeAccount);
            textViewValue = itemView.findViewById(R.id.textViewValue);
            textViewPayDay = itemView.findViewById(R.id.textViewPayDay);
        }
    }
}
