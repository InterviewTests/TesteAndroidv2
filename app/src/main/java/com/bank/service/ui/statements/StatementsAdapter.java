package com.bank.service.ui.statements;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.bank.service.R;
import com.bank.service.ui.statements.domain.model.Statements;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.MyViewHolder> {

    private List<Statements> stateList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView st_title, st_desc, st_date, st_value;

        public MyViewHolder(View view) {
            super(view);

            st_title = (TextView) view.findViewById(R.id.st_title);
            st_desc =  (TextView) view.findViewById(R.id.st_desc);
            st_date =  (TextView) view.findViewById(R.id.st_date);
            st_value = (TextView) view.findViewById(R.id.st_value);
        }
    }

    public StatementsAdapter(List<Statements> stateList) {
        this.stateList = stateList;
    }

    @Override // @NonNull
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                 View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statements_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Statements stm = stateList.get(position);

         holder.st_title.setText(stm.getTitle());
         holder.st_desc.setText(stm.getDesc());
         holder.st_date.setText(stm.getDate());
         holder.st_value.setText(stm.getValue());
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }
}
