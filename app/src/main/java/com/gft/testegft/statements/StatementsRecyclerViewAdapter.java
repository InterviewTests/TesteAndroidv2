package com.gft.testegft.statements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gft.testegft.R;
import com.gft.testegft.statements.data.Statement;

import java.util.List;

import static com.gft.testegft.statements.utils.DateFormatter.formatDate;

class StatementsRecyclerViewAdapter extends RecyclerView.Adapter<StatementsRecyclerViewAdapter.StatementViewHolder> {
    public List<Statement> statements;

    static class StatementViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView data;
        TextView value;

        StatementViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.statementTitle);
            desc = v.findViewById(R.id.statementDesc);
            data = v.findViewById(R.id.statementDate);
            value = v.findViewById(R.id.statementValue);
        }
    }

    StatementsRecyclerViewAdapter(List<Statement> statements) {
        this.statements = statements;
    }

    @NonNull
    @Override
    public StatementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StatementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.statement_item, parent, false));
    }

    @Override
    public void onBindViewHolder(StatementViewHolder holder, int position) {
        String value = "R$" + String.valueOf(statements.get(position).getValue());

        holder.title.setText(statements.get(position).getTitle());
        holder.desc.setText(statements.get(position).getDesc());
        holder.data.setText(formatDate(statements.get(position).getDate()));
        holder.value.setText(value);
    }

    @Override
    public int getItemCount() {
        return statements.size();
    }
}