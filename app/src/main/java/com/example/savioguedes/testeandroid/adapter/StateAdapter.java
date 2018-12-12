package com.example.savioguedes.testeandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savioguedes.testeandroid.R;
import com.example.savioguedes.testeandroid.model.Statement;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewLayout>{

    private List<Statement> items;
    private Context context;

    public StateAdapter(List<Statement> items, Context context){

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


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewLayout extends RecyclerView.ViewHolder{

        TextView payTitle, payValue;

        ViewLayout(@NonNull View itemView) {
            super(itemView);

            payTitle = itemView.findViewById(R.id.state_card_pay);
            payValue = itemView.findViewById(R.id.state_card_value);
        }
    }
}
