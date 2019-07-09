package ssilvalucas.testeandroidv2.screen.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ssilvalucas.testeandroidv2.R;
import ssilvalucas.testeandroidv2.data.model.StatementHome;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {
    private ArrayList<StatementHome> statements;
    private Context context;

    public StatementsAdapter(ArrayList<StatementHome> statements, Context context) {
        this.statements = statements;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_statement_home_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textViewTitle.setText(statements.get(position).getTitle());
        viewHolder.textViewDesc.setText(statements.get(position).getDesc());
        viewHolder.textViewDate.setText(statements.get(position).getFormatedDate());
        viewHolder.textViewValue.setText(statements.get(position).getFormatedValue());
    }

    @Override
    public int getItemCount() {
        return statements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  textViewTitle, textViewDesc, textViewDate, textViewValue;
        ConstraintLayout elementCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.item_statement_title);
            textViewDesc  = itemView.findViewById(R.id.item_statement_desc);
            textViewDate  = itemView.findViewById(R.id.item_statement_date);
            textViewValue = itemView.findViewById(R.id.item_statement_value);
            elementCard   = itemView.findViewById(R.id.item_statement_element_card);
        }
    }
}
