package com.everis.TesteAndroidv2.Statement.View;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.everis.TesteAndroidv2.Statement.Model.TransactionInfo;
import com.everis.TesteAndroidv2.R;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<TransactionInfo> mStatementList;

    LineAdapter(ArrayList<TransactionInfo> statementList){
        mStatementList = statementList;
    }

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.statement_transaction_line, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, int position) {
        holder.title.setText(mStatementList.get(position).getTitle());
        holder.description.setText(mStatementList.get(position).getDesc());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", new Locale("pt", "BR"));
        Date data = formato.parse(mStatementList.get(position).getDate(), new ParsePosition(0));
        formato.applyPattern("dd/MM/yyyy");
        holder.date.setText(formato.format(data));
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        format.setCurrency(Currency.getInstance("BRL"));
        holder.value.setText(format.format(mStatementList.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return mStatementList != null ? mStatementList.size() : 0;
    }
}
