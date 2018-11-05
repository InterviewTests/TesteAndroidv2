package br.com.santanderteste.ui.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.santanderteste.R;
import br.com.santanderteste.model.Statement;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.StatementViewHolder> {

    private List<Statement> statements = new ArrayList<>();

    public void setData(List<Statement> statements) {
        this.statements.clear();
        this.statements.addAll(statements);
    }

    public void addData(List<Statement> statements) {
        this.statements.addAll(statements);
    }

    public static class StatementViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.value)
        TextView value;

        @BindView(R.id.title)
        TextView title;

        public StatementViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @NonNull
    @Override
    public StatementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.statement_item, viewGroup, false);

        StatementViewHolder statementViewHolder = new StatementViewHolder(v);

        return statementViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatementViewHolder statementViewHolder, int i) {

        statementViewHolder.date.setText(statements.get(i).getDate());
        statementViewHolder.value.setText(statements.get(i).getValue());
        statementViewHolder.title.setText(statements.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return this.statements.size();
    }

}
