package com.br.web.glix.interviewgiovanipaleologo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.web.glix.interviewgiovanipaleologo.R;
import com.br.web.glix.interviewgiovanipaleologo.models.Statement;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementViewHolder> {

    private Context mContext;
    private List<Statement> statementList;

    LayoutInflater inflater;

    public StatementAdapter(Context context, List<Statement> statementList) {
        this.mContext = context;
        this.statementList = statementList;
    }

    @NonNull
    @Override
    public StatementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.recyclerview_statement, parent, false);

        return new StatementViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementViewHolder holder, int position) {
        holder.tvStatementTitle.setText(statementList.get(position).getTitle());
        holder.tvStatementDate.setText(DateFormat.getDateInstance().format(statementList.get(position).getDate()));
        holder.tvStatementDescription.setText(statementList.get(position).getDesc());
        holder.tvStatementValue.setText(NumberFormat.getCurrencyInstance().format(statementList.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return statementList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<Statement> statementList) {
        this.statementList = statementList;
        notifyDataSetChanged();
    }

    class StatementViewHolder extends RecyclerView.ViewHolder {

        public TextView tvStatementTitle;
        public TextView tvStatementDate;
        public TextView tvStatementDescription;
        public TextView tvStatementValue;

        public StatementViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStatementTitle = itemView.findViewById(R.id.tvStatementTitle);
            tvStatementDate = itemView.findViewById(R.id.tvStatementDate);
            tvStatementDescription = itemView.findViewById(R.id.tvStatementDescription);
            tvStatementValue = itemView.findViewById(R.id.tvStatementValue);
        }
    }
}
