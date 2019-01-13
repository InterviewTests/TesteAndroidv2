package com.resourceit.app.Adaptera;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.resourceit.app.R;
import com.resourceit.app.holders.StatmentHolder;
import com.resourceit.app.models.StatmentModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class StatmentsAdapter extends RecyclerView.Adapter<StatmentHolder> {

    private final List<StatmentModel> statments;

    public StatmentsAdapter(ArrayList statments) {
        this.statments = statments;
    }

    @Override
    public StatmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StatmentHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(StatmentHolder holder, int position) {
        holder.title.setText(statments.get(position).getTitle());
        holder.desc.setText(statments.get(position).getDesc());
        holder.date.setText(statments.get(position).getDate());
        holder.value.setText("R$"+statments.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return statments != null ? statments.size() : 0;
    }

    public void insertItem(StatmentModel statment) {
        statments.add(statment);
        notifyItemInserted(getItemCount());
    }

}
