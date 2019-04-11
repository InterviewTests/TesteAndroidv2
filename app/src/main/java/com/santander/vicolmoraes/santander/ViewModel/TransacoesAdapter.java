package com.santander.vicolmoraes.santander.ViewModel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santander.vicolmoraes.santander.Model.TransacaoVO;
import com.santander.vicolmoraes.santander.R;

import java.util.ArrayList;

public class TransacoesAdapter extends RecyclerView.Adapter<TransacoesAdapter.TransacoesViewHolder> {


    private ArrayList<TransacaoVO> listaTransacoes;

    public TransacoesAdapter(ArrayList<TransacaoVO> lista) {

        listaTransacoes = lista;
        notifyDataSetChanged();
    }

    @Override
    public TransacoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transacao, parent, false);
        return new TransacoesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransacoesViewHolder holder, int position) {

        holder.tipo.setText(listaTransacoes.get(position).getTitulo());
        holder.descricao.setText(listaTransacoes.get(position).getDescricao());
        holder.data.setText(android.text.format.DateFormat.format("dd/MM/yyyy", listaTransacoes.get(position).getData()).toString());
        holder.valor.setText(String.valueOf(listaTransacoes.get(position).getValue()));

    }

    @Override
    public int getItemCount() {
        return listaTransacoes.size();
    }

    public class TransacoesViewHolder extends RecyclerView.ViewHolder {
        private TextView tipo;
        private TextView descricao;
        private TextView data;
        private TextView valor;

        TransacoesViewHolder(View itemView) {
            super(itemView);
            tipo = itemView.findViewById(R.id.tv_transacao_tipo);
            descricao = itemView.findViewById(R.id.tv_transacao_tipo_nome);
            data = itemView.findViewById(R.id.tv_transacao_data);
            valor = itemView.findViewById(R.id.tv_transacao_valor);
        }
    }
}


