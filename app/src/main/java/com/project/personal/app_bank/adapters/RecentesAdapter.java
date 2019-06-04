package com.project.personal.app_bank.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.personal.app_bank.R;
import com.project.personal.app_bank.models.Statements;

import java.sql.Statement;
import java.util.List;

public class RecentesAdapter extends RecyclerView.Adapter<RecentesAdapter.ListViewHolder> {
    private List<Statements> list;
    private Context context;

    public RecentesAdapter(List<Statements> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list, parent, false);
        ListViewHolder holder = new ListViewHolder(view);

        return holder;

    }


    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int i) {

        //Atualiza a view
        Statements s = list.get(i);

        holder.title.setText(s.getTitle());
        holder.desc.setText(s.getDesc());
        holder.date.setText(s.getDate());
        holder.value.setText("R$ "+s.getValue());
    }

    //View Holder com as Views

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView title;
        public TextView desc;
        public TextView date;
        public TextView value;

        public ListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            desc = itemView.findViewById(R.id.textViewDesc);
            date = itemView.findViewById(R.id.textViewDate);
            value = itemView.findViewById(R.id.textViewValue);
            cardView = itemView.findViewById(R.id.CardViewList);

        }
    }

}
