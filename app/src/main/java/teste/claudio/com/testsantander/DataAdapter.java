package teste.claudio.com.testsantander;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<statementList> android;

    private DecimalFormat nf = new DecimalFormat("#,###.00");

    public DataAdapter(ArrayList<statementList> android) {
        this.android = android;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.titulo.setText(android.get(position).getTitle());

        holder.descricao.setText(android.get(position).getDesc());

        String dataF = android.get(position).getDate().replaceAll("-", "/");
        String[] s = dataF.split("/");
        String novaData = s[2]+"/"+s[1]+"/"+s[0];
        holder.data.setText(novaData);

        holder.valor.setText("R$ " + nf.format(android.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView descricao;
        private TextView data;
        private TextView valor;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView)itemView.findViewById(R.id.titulo);
            descricao = (TextView)itemView.findViewById(R.id.descricao);
            data = (TextView)itemView.findViewById(R.id.txtDate);
            valor = (TextView) itemView.findViewById(R.id.valor);

        }
    }
}
