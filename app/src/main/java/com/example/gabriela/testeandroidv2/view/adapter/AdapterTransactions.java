package com.example.gabriela.testeandroidv2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gabriela.testeandroidv2.R;
import com.example.gabriela.testeandroidv2.model.StatementList;
import com.example.gabriela.testeandroidv2.util.DateUtil;
import com.example.gabriela.testeandroidv2.view.ui.InfoActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterTransactions extends BaseAdapter {

    public LayoutInflater layoutInflater;
    public ArrayList<StatementList> statementList;
    View view;

    public AdapterTransactions(InfoActivity infoActivity, int item_list,ArrayList<StatementList> statementList) {

        this.statementList = statementList;

        layoutInflater = LayoutInflater.from(infoActivity);

    }

    @Override
    public int getCount() {
        return statementList.size();
    }

    @Override
    public Object getItem(int i) {
        return statementList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        StatementList item = statementList.get(i);
        convertView = layoutInflater.inflate(R.layout.item_list, null);

        TextView titulo = convertView.findViewById(R.id.nome_transacao);
        TextView data = convertView.findViewById(R.id.data_transacao);
        TextView conta = convertView.findViewById(R.id.conta);
        TextView descricao = convertView.findViewById(R.id.preco);

        titulo.setText(item.title);
        data.setText(DateUtil.getDataBr(item.date));
        conta.setText(item.desc);
        descricao.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(item.value)));


        return convertView;
    }


}
