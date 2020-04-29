package com.br.example.fakebank.presentations.views.adpters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.example.fakebank.databinding.RvItemStatementBinding;
import com.br.example.fakebank.infrastructure.retrofit.entities.CurrencyEntity;
import com.br.example.fakebank.presentations.views.holders.StatementHolder;

import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementHolder> {
    private List<CurrencyEntity> currencyEntityList;

    public StatementAdapter(List<CurrencyEntity> currencyEntityList) {
        this.currencyEntityList = currencyEntityList;
    }

    @NonNull
    @Override
    public StatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvItemStatementBinding rvItemStatementBinding = RvItemStatementBinding.inflate(layoutInflater, parent,false);
        return new StatementHolder(rvItemStatementBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementHolder holder, int position) {
        CurrencyEntity currencyEntity = currencyEntityList.get(position);
        holder.bind(currencyEntity);
    }

    @Override
    public int getItemCount() {
        return currencyEntityList.size();
    }
}
