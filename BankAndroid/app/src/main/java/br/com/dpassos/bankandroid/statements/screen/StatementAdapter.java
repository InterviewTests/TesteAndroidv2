package br.com.dpassos.bankandroid.statements.screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.dpassos.bankandroid.R;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementViewHolder> {
    List<StatementViewObject> statements;
    @NonNull
    @Override
    public StatementAdapter.StatementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.statement_list_item, parent, false);
        return new StatementViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementViewHolder holder, int position) {
        StatementViewObject statementViewObject = statements.get(position);
        holder.itemStatementValue.setText(statementViewObject.value);
        holder.itemStatementDate.setText(statementViewObject.date);
        holder.itemStatementTitle.setText(statementViewObject.title);
        holder.itemStatementDesc.setText(statementViewObject.desc);
    }

    @Override
    public int getItemCount() {
        return statements.size();
    }

    static class StatementViewHolder extends  RecyclerView.ViewHolder {
        TextView itemStatementTitle, itemStatementDesc, itemStatementDate, itemStatementValue;

        public StatementViewHolder(@NonNull View itemView) {
            super(itemView);
            itemStatementValue = itemView.findViewById(R.id.itemStatementValue);
            itemStatementDesc = itemView.findViewById(R.id.itemStatementDesc);
            itemStatementDate = itemView.findViewById(R.id.itemStatementDate);
            itemStatementTitle = itemView.findViewById(R.id.itemStatementTitle);
        }
    }
}