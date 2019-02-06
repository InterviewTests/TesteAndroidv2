package com.luizroseiro.testeandroidv2.mainScreen;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.math.MathUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luizroseiro.testeandroidv2.MainActivity;
import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.models.StatementModel;

import java.math.MathContext;
import java.util.List;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsViewHolder> {

    private List<StatementModel> statements;

    StatementsAdapter(List<StatementModel> statements) {
        this.statements = statements;
    }

    @NonNull
    @Override
    public StatementsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_statement,
                viewGroup, false);
        return new StatementsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementsViewHolder statementsViewHolder, int i) {
        statementsViewHolder.setStatement(statements.get(i));
    }

    @Override
    public int getItemCount() {
        return statements == null ? 0 : statements.size();
    }

}

class StatementsViewHolder extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvDate;
    private TextView tvValue;

    StatementsViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDesc = itemView.findViewById(R.id.tv_desc);
        tvDate = itemView.findViewById(R.id.tv_date);
        tvValue = itemView.findViewById(R.id.tv_value);
    }

    void setStatement(StatementModel statement) {
        if (statement.getValue() < 0)
            tvValue.setTextColor(ContextCompat.getColor(MainActivity.getContext(),
                    R.color.colorNegativeValue));
        else
            tvValue.setTextColor(ContextCompat.getColor(MainActivity.getContext(),
                    R.color.colorPositiveValue));

        tvTitle.setText(statement.getTitle());
        tvDesc.setText(statement.getDesc());
        tvDate.setText(statement.getDate());
        tvValue.setText(MainActivity.getContext().getString(R.string.balance_value,
                Math.abs(statement.getValue())));
    }

}