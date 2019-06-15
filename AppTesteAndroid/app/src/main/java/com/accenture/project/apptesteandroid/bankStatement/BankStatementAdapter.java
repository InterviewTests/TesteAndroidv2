package com.accenture.project.apptesteandroid.bankStatement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accenture.project.apptesteandroid.R;
import com.accenture.project.apptesteandroid.model.BankStatement;


import java.util.ArrayList;
import java.util.List;

public class BankStatementAdapter extends RecyclerView.Adapter<BankStatementAdapter.MyViewHolder> {

    private Context context;
    private List<BankStatement> bankStatementList = new ArrayList<>();

    public BankStatementAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<BankStatement> statmentList) {
        this.bankStatementList.clear();
        this.bankStatementList.addAll(statmentList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view
        View view = LayoutInflater.from(context).inflate(R.layout.item_statment_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BankStatement statment = bankStatementList.get(position);
        holder.tvTitle.setText(statment.getTitle());
        holder.tvDate.setText(statment.getDate());
        holder.tvDesc.setText(statment.getDesc());
        holder.tvValue.setText(statment.getValue());
    }

    @Override
    public int getItemCount() {
       return bankStatementList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView tvTitle, tvDate, tvDesc, tvValue;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvTitle = view.findViewById(R.id.tv_title);
            tvDate = view.findViewById(R.id.tv_date);
            tvDesc = view.findViewById(R.id.tv_description);
            tvValue = view.findViewById(R.id.tv_value);

        }
    }
}
