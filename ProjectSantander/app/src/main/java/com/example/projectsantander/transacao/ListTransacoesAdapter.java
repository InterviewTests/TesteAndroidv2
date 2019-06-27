package com.example.projectsantander.transacao;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectsantander.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListTransacoesAdapter extends BaseAdapter {

    private List<Transacao> lista;
    private Activity activity;

    public ListTransacoesAdapter(List<Transacao> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = activity.getLayoutInflater()
                .inflate(R.layout.item_transacao, viewGroup, false);
        SimpleDateFormat sdfLeitura = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfEscrita = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = "";
        Transacao t = (Transacao) getItem(i);
        try {
            Date data = sdfLeitura.parse(t.getDate());
            dataFormatada = sdfEscrita.format(data);
        } catch (ParseException e) {

        }

        ((TextView)view.findViewById(R.id.edtDesc)).setText(t.getDesc());
        ((TextView)view.findViewById(R.id.edtData)).setText(dataFormatada);
        ((TextView)view.findViewById(R.id.edtTitulo)).setText(t.getTitle());
        ((TextView)view.findViewById(R.id.edtValor)).setText(NumberFormat.getCurrencyInstance().format(t.getValue())
                .toString().replace("(","-")
        .replace(")",""));
        return view;
    }
}
