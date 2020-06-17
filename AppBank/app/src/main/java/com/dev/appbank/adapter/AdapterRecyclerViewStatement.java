package com.dev.appbank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.appbank.R;
import com.dev.appbank.model.Statement;
import com.dev.appbank.util.Common;

import java.util.List;

public class AdapterRecyclerViewStatement extends RecyclerView.Adapter<AdapterRecyclerViewStatement.MyViewHolder> {

    private List<Statement> statementList;

    public AdapterRecyclerViewStatement(List<Statement> statementList) {
        this.statementList = statementList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_statement,
                parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Statement statement = statementList.get(position);
        holder.txtTitle.setText(statement.getTitle());
        holder.txtDescription.setText(statement.getDesc());
        holder.txtDate.setText(convertDateString(statement.getDate()));
        holder.txtValue.setText(Common.NUMBER_FORMAT.format(statement.getValue()));
    }

    @Override
    public int getItemCount() {
        return statementList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle, txtDate, txtDescription, txtValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle =itemView.findViewById(R.id.txt_title);
            txtDate =itemView.findViewById(R.id.txt_date);
            txtDescription =itemView.findViewById(R.id.txt_description);
            txtValue =itemView.findViewById(R.id.txt_value);
        }
    }

    public static String convertDateString(String data){
        String[] array = data.split("-");
        String dataFormatada = array[2] + "/" + array[1] + "/"  + array[0];
        return dataFormatada;
    }

}
