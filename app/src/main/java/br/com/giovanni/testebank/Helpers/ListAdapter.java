package br.com.giovanni.testebank.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.giovanni.testebank.Model.Transacao;
import br.com.giovanni.testebank.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Transacao> listExamples;
    private Context context;

    public ListAdapter(List<Transacao> listExamples, Context context) {
        this.listExamples = listExamples;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transacao list = listExamples.get(position);

        holder.txtTitle.setText(list.getTitle());
        holder.txtDescricao.setText(list.getDescricao());
        holder.txtValue.setText(String.format("R$ %.2f",list.getValue()));
        holder.txtDate.setText(list.getDate());
        // value error, transform to string
    }

    @Override
    public int getItemCount() {
        return listExamples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;
        public TextView txtDescricao;
        public TextView txtValue;
        public TextView txtDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitleId);
            txtDescricao = itemView.findViewById(R.id.txtDescricaoId);
            txtValue = itemView.findViewById(R.id.txtValueId);
            txtDate = itemView.findViewById(R.id.txtDateId);

        }
    }

}
