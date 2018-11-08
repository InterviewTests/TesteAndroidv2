package com.luizroseiro.testeandroidv2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.models.StatementData;

import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentViewHolder> {

    private List<StatementData> statements;

    public RecentAdapter(List<StatementData> statements) {
        this.statements = statements;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_statement,
                viewGroup, false);
        return new RecentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder recentViewHolder, int i) {
        StatementData statement = statements.get(i);
        recentViewHolder.setStatement(statement);
    }

    @Override
    public int getItemCount() {
        return statements == null ? 0 : statements.size();
    }

}

class RecentViewHolder extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvDate;
    private TextView tvValue;

    RecentViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDesc = itemView.findViewById(R.id.tv_desc);
        tvDate = itemView.findViewById(R.id.tv_date);
        tvValue = itemView.findViewById(R.id.tv_value);
    }

    void setStatement(StatementData statement) {
        tvTitle.setText(statement.getTitle());
        tvDesc.setText(statement.getDesc());
        tvDate.setText(statement.getFormattedDate());
        tvValue.setText(String.valueOf(statement.getValue()));
    }

}
