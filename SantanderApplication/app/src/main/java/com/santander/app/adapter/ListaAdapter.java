package com.santander.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santander.app.R;
import com.santander.app.bean.Registro;
import com.santander.app.util.Convert;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private Context context;
    private List<Registro> registros;

    public ListaAdapter(Context context, List<Registro> registros){
        this.context = context;
        this.registros = registros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Registro registro = registros.get(i);
        viewHolder.tvValorEntrada.setText("R$ " + String.format("%.2f", registro.getValue()));
        viewHolder.tvEntrada.setText(registro.getDesc());
        viewHolder.tvTipoEntrada.setText(registro.getTitle());
        viewHolder.tvDataEntrada.setText(Convert.UsDateToBrDate(registro.getDate()));
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTipoEntrada;
        TextView tvDataEntrada;
        TextView tvEntrada;
        TextView tvValorEntrada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTipoEntrada = itemView.findViewById(R.id.tvTipoEntrada);
            tvDataEntrada = itemView.findViewById(R.id.tvDataEntrada);
            tvEntrada = itemView.findViewById(R.id.tvEntrada);
            tvValorEntrada = itemView.findViewById(R.id.tvValorEntrada);
        }
    }
}
