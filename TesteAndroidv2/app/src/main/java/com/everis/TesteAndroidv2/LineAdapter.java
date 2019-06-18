package com.everis.TesteAndroidv2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<Statements> mExtratos;

    public LineAdapter(ArrayList<Statements> extratos){
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
        holder.data.setText(mExtratos.get(position).getDate());
        holder.valor.setText(mExtratos.get(position).getValue().toString());
    }

    @Override
    public int getItemCount() {
        return mExtratos != null ? mExtratos.size() : 0;
    }
}
