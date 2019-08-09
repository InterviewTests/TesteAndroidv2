package Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testesantander.R;

import java.util.List;

import Models.Detalhe;

public class ListaDetalhesAdapter extends RecyclerView.Adapter {
    private List<Detalhe> lista;
    private Context context;

    public ListaDetalhesAdapter(Context context, List<Detalhe> lista){
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
        LayoutInflater.from(context).inflate(R.layout.detail_item, parent, false);
        return new DetalheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Detalhe detalhe = lista.get(position);
        // Montando elementos
        TextView tvTitle = holder.itemView.findViewById(R.id.tvTitle);
        TextView tvDesc = holder.itemView.findViewById(R.id.tvDesc);
        TextView tvDate = holder.itemView.findViewById(R.id.tvDate);
        TextView tvValue = holder.itemView.findViewById(R.id.tvValue);

        tvTitle.setText(detalhe.getTitle());
        tvDesc.setText(detalhe.getDesc());
        tvDate.setText(detalhe.getDate());
        tvValue.setText(String.format("R$%.2f", detalhe.getValue()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class DetalheViewHolder extends RecyclerView.ViewHolder{

        public DetalheViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
