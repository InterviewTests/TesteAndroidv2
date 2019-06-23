package com.example.appbank.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appbank.R;
import com.example.appbank.data.remote.model.Statement;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdapterAccount extends RecyclerView.Adapter<AdapterAccount.MyViewHolder> {

    private Context context;
    private List<Statement> statements;

    public AdapterAccount(Context context, List<Statement> statements) {
        this.context = context;
        this.statements = statements;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemList = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_account_adapter, viewGroup, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Statement statement = statements.get(i);
        myViewHolder.textViewTitle.setText(statement.getTitle());

        myViewHolder.textViewDesc.setText(statement.getDesc());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(statement.getDate());
        myViewHolder.textViewDate.setText(strDate);

        Locale locale = new Locale( "pt", "BR" );
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        String currency = format.format(statement.getValue());
        myViewHolder.textViewValue.setText(currency);

    }

    @Override
    public int getItemCount() {
        return statements.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewDesc;
        private TextView textViewValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewValue = itemView.findViewById(R.id.textViewValue);
        }
    }
}
