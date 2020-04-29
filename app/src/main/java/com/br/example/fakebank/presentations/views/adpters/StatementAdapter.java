package com.br.example.fakebank.presentations.views.adpters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.example.fakebank.databinding.RvItemStatementBinding;
import com.br.example.fakebank.infrastructure.retrofit.entities.StatementEntity;
import com.br.example.fakebank.presentations.views.holders.StatementHolder;

import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementHolder> {
    private List<StatementEntity> statementEntityList;

    public StatementAdapter(List<StatementEntity> statementEntityList) {
        this.statementEntityList = statementEntityList;
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
        StatementEntity statementEntity = statementEntityList.get(position);
        holder.bind(statementEntity);
    }

    @Override
    public int getItemCount() {
        return statementEntityList.size();
    }
}
