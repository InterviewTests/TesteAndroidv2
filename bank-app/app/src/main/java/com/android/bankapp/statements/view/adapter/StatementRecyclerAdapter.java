package com.android.bankapp.statements.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.bankapp.R;
import com.android.bankapp.statements.model.Statement;

import java.util.List;

public class StatementRecyclerAdapter extends RecyclerView.Adapter<StatementRecyclerAdapter.BasicViewHolder> {

    private List<Statement> statementList;

    public StatementRecyclerAdapter() {
    }

    @NonNull
    @Override
    public BasicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BasicViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.statement_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder holder, int position) {
        holder.onBind(statementList.get(position));
    }

    @Override
    public int getItemCount() {
        return statementList == null? 0: statementList.size();
    }

    public void addAll(List<Statement> statementList){
        this.statementList = statementList;
        notifyDataSetChanged();
    }

    public class BasicViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public BasicViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.test);
        }

        public void onBind(Statement model) {
            title.setText(model.getTitle());
        }
    }
}
