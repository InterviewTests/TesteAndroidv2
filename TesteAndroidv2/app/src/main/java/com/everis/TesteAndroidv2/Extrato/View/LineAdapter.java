package com.everis.TesteAndroidv2.Extrato.View;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.everis.TesteAndroidv2.Extrato.Model.Statements;
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

    private final List<Statements> mExtratos;

    LineAdapter(ArrayList<Statements> extratos){
        mExtratos = extratos;
    }

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.extrato_lancamento_line, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, int position) {
        holder.titulo.setText(mExtratos.get(position).getTitle());
        holder.descricao.setText(mExtratos.get(position).getDesc());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", new Locale("pt", "BR"));
        Date data = formato.parse(mExtratos.get(position).getDate(), new ParsePosition(0));
        formato.applyPattern("dd/MM/yyyy");
        holder.data.setText(formato.format(data));
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        format.setCurrency(Currency.getInstance("BRL"));
        holder.valor.setText(format.format(mExtratos.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return mExtratos != null ? mExtratos.size() : 0;
    }
}
