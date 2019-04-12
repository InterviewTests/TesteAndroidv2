package com.android.bankapp.statements.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.bankapp.R;
import com.android.bankapp.statements.model.Statement;
import com.android.bankapp.statements.view.StatementsActivity;

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
        private TextView textViewStatementTitle, textViewStatementDate, textViewStatementDescription, textViewStatementValue;

        public BasicViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewStatementTitle = itemView.findViewById(R.id.txt_view_statement_title);
            this.textViewStatementDate = itemView.findViewById(R.id.txt_view_statement_date);
            this.textViewStatementValue = itemView.findViewById(R.id.txt_view_statement_value);
            this.textViewStatementDescription = itemView.findViewById(R.id.txt_view_statement_description);
        }

        public void onBind(Statement model) {
            textViewStatementTitle.setText(model.getTitle());
            textViewStatementDate.setText(model.getDate());
            textViewStatementDescription.setText(model.getDesc());
            textViewStatementValue.setText(StatementsActivity.formatCurrencyValue(model.getValue()));
        }
    }
}
