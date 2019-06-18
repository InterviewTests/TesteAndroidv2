package com.everis.TesteAndroidv2.Extrato.View;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.everis.TesteAndroidv2.R;

class LineHolder extends RecyclerView.ViewHolder {

    TextView titulo;
    TextView descricao;
    TextView data;
    TextView valor;

    LineHolder(@NonNull View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.lancamento_titulo);
        descricao = itemView.findViewById(R.id.lancamento_descricao);
        data = itemView.findViewById(R.id.lancamento_data);
        valor = itemView.findViewById(R.id.lancamento_valor);
    }
}
