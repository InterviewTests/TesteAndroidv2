package com.example.projectbank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectbank.Model.Statement;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Statement statements;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Statement statements, Context c) {
        this.statements = statements;
        this.layoutInflater = ((LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_statement, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.txtTitle.setText(statements.getStatementList().get(i).getTitle());
        viewHolder.txtDate.setText(statements.getStatementList().get(i).getDate());
        viewHolder.txtDesc.setText(statements.getStatementList().get(i).getDesc());
        viewHolder.txtValue.setText(String.valueOf(statements.getStatementList().get(i).getValue()));
    }

    @Override
    public int getItemCount() {
        return statements.getStatementList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;
        TextView txtTitle;
        TextView txtDate;
        TextView txtDesc;
        TextView txtValue;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.llItemStatement);
            txtTitle = itemView.findViewById(R.id.tytleId);
            txtDate = itemView.findViewById(R.id.dateId);
            txtDesc = itemView.findViewById(R.id.descId);
            txtValue = itemView.findViewById(R.id.valueId);
        }
    }

}
