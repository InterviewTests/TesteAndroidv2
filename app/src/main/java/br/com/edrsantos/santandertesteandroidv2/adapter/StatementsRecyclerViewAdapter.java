package br.com.edrsantos.santandertesteandroidv2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.edrsantos.santandertesteandroidv2.R;
import br.com.edrsantos.santandertesteandroidv2.model.Statement;
import br.com.edrsantos.santandertesteandroidv2.util.Util;

public class StatementsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Statement> statementList;
    Context mContext;
    NumberFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));

    public StatementsRecyclerViewAdapter(List<Statement> statementList, Context mContext){
        this.statementList = statementList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        View v = inflater.inflate(R.layout.item_statement, null, false);
        v.setLayoutParams(layoutParams);
        viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;

        Statement s = statementList.get(position);

        viewHolder.title.setText(s.getTitle());
        viewHolder.desc.setText(s.getDesc());
        viewHolder.date.setText(Util.convertDateToString(s.getDate()));

        String value = decimalFormat.format(s.getValue());
        viewHolder.value.setText(value);
    }

    @Override
    public int getItemCount() {
        return statementList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView desc;
        TextView date;
        TextView value;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            date = (TextView) itemView.findViewById(R.id.date);
            value = (TextView) itemView.findViewById(R.id.value);

        }
    }

}
