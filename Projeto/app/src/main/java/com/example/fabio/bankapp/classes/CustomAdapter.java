package com.example.fabio.bankapp.classes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fabio.bankapp.R;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<DataRecentes> recentesList;
    private Locale ptBr = new Locale("pt", "BR");

    public CustomAdapter(Context context, List<DataRecentes> recentesList) {
        this.context = context;
        this.recentesList = recentesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_recentes, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String datastr, valorstr;
        Double valor;
        holder.txttitle.setText(recentesList.get(position).getTitle());
        holder.txtdesc.setText(recentesList.get(position).getDesc());

        datastr = recentesList.get(position).getDate();
        DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = formatUS.parse(datastr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
        String dateFormated = formatBR.format(date);
        holder.txtdate.setText(dateFormated);

        valor = recentesList.get(position).getValue();
        valorstr = NumberFormat.getCurrencyInstance(ptBr).format(valor);
        holder.txtvalue.setText(valorstr);
    }

    @Override
    public int getItemCount() {
        return recentesList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView txttitle, txtdesc, txtdate, txtvalue;

        public ViewHolder(View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.txtTitle);
            txtdesc = itemView.findViewById(R.id.txtDesc);
            txtdate = itemView.findViewById(R.id.txtDate);
            txtvalue = itemView.findViewById(R.id.txtValue);
        }
    }
}
