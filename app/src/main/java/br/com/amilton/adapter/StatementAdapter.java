package br.com.amilton.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.amilton.R;
import br.com.amilton.databinding.StatementRowBinding;
import br.com.amilton.model.Statement;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementViewHolder> {

    private final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("en", "US"));
    private List<Statement> list = new ArrayList<>();

    public StatementAdapter() {
        decimalFormat.applyPattern(String.format("%s ##,##0.00", NumberFormat.getCurrencyInstance().getCurrency().getSymbol()));
    }

    @NonNull
    @Override
    public StatementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StatementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.statement_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatementViewHolder holder, int position) {
        Statement statements = list.get(position);
        holder.statementRowBinding.tvDate.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(statements.getDate()));
        holder.statementRowBinding.tvDecription.setText(statements.getDesc());
        holder.statementRowBinding.tvTitle.setText(statements.getTitle());
        holder.statementRowBinding.tvValue.setText(decimalFormat.format(statements.getValue()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Statement> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class StatementViewHolder extends RecyclerView.ViewHolder {
        private final StatementRowBinding statementRowBinding;
        StatementViewHolder(View v) {
            super(v);
            statementRowBinding = StatementRowBinding.bind(v);
        }
    }
}
