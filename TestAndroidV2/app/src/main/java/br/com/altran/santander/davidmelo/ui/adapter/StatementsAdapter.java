package br.com.altran.santander.davidmelo.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.altran.santander.davidmelo.R;
import br.com.altran.santander.davidmelo.model.StatementList;

import java.text.NumberFormat;
import java.util.ArrayList;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {
    private ArrayList<StatementList> list;

    public StatementsAdapter(ArrayList<StatementList> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StatementsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bill, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StatementsAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.type.setText(list.get(position).getDesc());
        holder.date.setText(list.get(position).getDate());
        holder.value.setText(NumberFormat.getCurrencyInstance().format(list.get(position).getValue()) + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView type;
        TextView date;
        TextView value;

        ViewHolder(View v) {
            super(v);
                value = v.findViewById(R.id.value_price);
                title = v.findViewById(R.id.payment_text);
                type = v.findViewById(R.id.type_text);
                date = v.findViewById(R.id.payment_date);


        }
    }
}
