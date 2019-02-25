package com.brunorfreitas.teste_ibm_santander.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.brunorfreitas.teste_ibm_santander.R;


import com.brunorfreitas.teste_ibm_santander.model.Statement;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import okhttp3.internal.Util;

public class AdapterStatements extends RecyclerView.Adapter<AdapterStatements.ViewHolder>{

    private List<Statement> listStatement;
    private Context context;

    public AdapterStatements(List<Statement> listStatement, Context context) {
        this.listStatement = listStatement;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_statement, parent, false);
        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Statement statement = listStatement.get(position);
        holder.bind(statement, position);

    }

    @Override
    public int getItemCount() {
        return listStatement.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        private TextView title, desc, date, value;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;

            title = itemView.findViewById(R.id.item_statement_tv_tile);
            desc = itemView.findViewById(R.id.item_statement_tv_desc);
            date = itemView.findViewById(R.id.item_statement_tv_date);
            value = itemView.findViewById(R.id.item_statement_value);

        }

        public void bind(Statement statement, final int position) {
            title.setText(statement.getTitle());
            desc.setText(statement.getDesc());
            date.setText(statement.getDate());
//            value.setText(String.valueOf(statement.getValue()));

            double value1 = statement.getValue() ;

            NumberFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));
            String valorFormatado = decimalFormat.format(value1);
            value.setText(valorFormatado);





//            DecimalFormat df = new DecimalFormat("###.###.###.###.###,00");
//            String valorFormatado = NumberFormat.getCurrencyInstance().format(12005.11);
//            String valorFormatado = new DecimalFormat("###,###,##0.00").format(12005.11);

//            value.setText(valorFormatado.replace(",",".").replace(".",","));
//                    .replace(".",",").replace(",","."));
//            .replace(",","."). replace(".",",")
        }
    }
}
