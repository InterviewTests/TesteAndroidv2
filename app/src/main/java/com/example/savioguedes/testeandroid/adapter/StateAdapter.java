package com.example.savioguedes.testeandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savioguedes.testeandroid.R;
import com.example.savioguedes.testeandroid.model.StatementList;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewLayout>{

    private List<StatementList> items;
    private Context context;

    public StateAdapter(List<StatementList> items, Context context){

        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewLayout onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.states_card_layout, parent, false);

        ViewLayout view = new ViewLayout(v);

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewLayout viewLayout, int i) {

        viewLayout.payTitle.setText(items.get(i).getTitle());
        viewLayout.payDesc.setText(items.get(i).getDesc());
        viewLayout.payDate.setText(items.get(i).getDate());
        viewLayout.payValue.setText(String.valueOf(items.get(i).getValue()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewLayout extends RecyclerView.ViewHolder{

        TextView payTitle, payDesc, payDate, payValue;

        ViewLayout(@NonNull View itemView) {
            super(itemView);

            payTitle = itemView.findViewById(R.id.state_card_pay_title);
            payDesc = itemView.findViewById(R.id.state_card_pay_desc);
            payDate = itemView.findViewById(R.id.state_card_pay_date);
            payValue = itemView.findViewById(R.id.state_card_value);
        }
    }
}
