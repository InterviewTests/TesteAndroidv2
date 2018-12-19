package com.example.jcsantos.santanderteste.Modules.Statements;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jcsantos.santanderteste.Components.Objects.Statement;
import com.example.jcsantos.santanderteste.R;

import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder> {
    private List<Statement> statement_list;
    private Context context;

    public StatementAdapter(Context context, List<Statement> statement_list) {
        this.statement_list   = statement_list;
        this.context    = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.statement_fragment, viewGroup, false);
        return new ViewHolder(view, statement_list);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(String.format("%s",statement_list.get(i).getTitle()));
        viewHolder.description.setText(String.format("%s",statement_list.get(i).getDescription()));
        viewHolder.date.setText(String.format("%s",statement_list.get(i).getDate()));
        viewHolder.value.setText(String.format("R$ %s",statement_list.get(i).getValue()));
    }

    @Override
    public int getItemCount() {
        return statement_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private List<Statement> statement_list;
        private TextView title;
        private TextView description;
        private TextView date;
        private TextView value;

        public ViewHolder(final View view, List<Statement> statement_list) {
            super(view);
            view.setOnClickListener(this);

            this.statement_list = statement_list;
            this.title = view.findViewById(R.id.txt_title);
            this.description = view.findViewById(R.id.txt_description);
            this.date = view.findViewById(R.id.txt_date);
            this.value = view.findViewById(R.id.txt_value);
        }

        @Override
        public void onClick(View v) {
        }

    }
}
