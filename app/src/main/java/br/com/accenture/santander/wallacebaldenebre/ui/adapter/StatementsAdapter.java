package br.com.accenture.santander.wallacebaldenebre.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.accenture.santander.wallacebaldenebre.R;
import br.com.accenture.santander.wallacebaldenebre.model.StatementList;

import java.text.NumberFormat;
import java.util.ArrayList;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {
    private ArrayList<StatementList> list;

    public StatementsAdapter(ArrayList<StatementList> list) {
        this.list = list;
    }

    @Override
    public StatementsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bill, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(StatementsAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.type.setText(list.get(position).getDesc());
        holder.date.setText(list.get(position).getDate());
        holder.value.setText(NumberFormat.getCurrencyInstance().format(list.get(position).getValue()) + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView type;
        public TextView date;
        public TextView value;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tvw_item_bill_title);
            type = v.findViewById(R.id.tvw_item_bill_type);
            date = v.findViewById(R.id.tvw_item_bill_date);
            value = v.findViewById(R.id.tvw_item_bill_price);
        }
    }
}
