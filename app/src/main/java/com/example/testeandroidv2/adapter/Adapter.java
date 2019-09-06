package com.example.testeandroidv2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.model.Lancamento;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Lancamento> listaLancamentos;

    public Adapter(List<Lancamento> lista) {
        this.listaLancamentos = (List<Lancamento>) lista;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lancamento, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Lancamento lancamento = listaLancamentos.get(position);
        holder.categoria.setText(lancamento.getCategoria());
        holder.conta.setText(lancamento.getConta());
        holder.data.setText(lancamento.getData());
        holder.valor.setText(lancamento.getValor());

    }

    @Override
    public int getItemCount() {
        return listaLancamentos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView categoria;
        TextView conta;
        TextView data;
        TextView valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categoria = itemView.findViewById(R.id.textCategoria);
            conta = itemView.findViewById(R.id.textConta);
            data = itemView.findViewById(R.id.textData);
            valor = itemView.findViewById(R.id.textValor);

        }
    }

}
