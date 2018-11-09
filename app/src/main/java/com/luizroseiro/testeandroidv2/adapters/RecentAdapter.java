package com.luizroseiro.testeandroidv2.adapters;

import android.content.Context;
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
    private Context context;

    public RecentAdapter(List<StatementData> statements, Context context) {
        this.statements = statements;
        this.context = context;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_statement,
                viewGroup, false);
        return new RecentViewHolder(view, context);
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

    private Context context;

    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvDate;
    private TextView tvValue;

    RecentViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDesc = itemView.findViewById(R.id.tv_desc);
        tvDate = itemView.findViewById(R.id.tv_date);
        tvValue = itemView.findViewById(R.id.tv_value);
    }

    void setStatement(StatementData statement) {
        tvTitle.setText(statement.getTitle());
        tvDesc.setText(statement.getDesc());
        tvDate.setText(statement.getFormattedDate());

        boolean expense = statement.getValue() < 0;
        if (expense) tvValue.setTextColor(context.getResources().getColor(R.color.colorRed));
        else tvValue.setTextColor(context.getResources().getColor(R.color.colorGreen));

        tvValue.setText(context.getString(R.string.balance_data, Math.abs(statement.getValue())));
    }

}
