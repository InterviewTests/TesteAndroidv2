package com.example.testeandroidv2.statementScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.databinding.TransactionHistoryItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<StatementModel> listOfStatement;

    public TransactionHistoryAdapter(List<StatementModel> listOfStatement){
        this.listOfStatement = listOfStatement;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_history_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(listOfStatement.get(i));
    }

    @Override
    public int getItemCount() {
        return listOfStatement.size();
    }

    public void setListOfStatement(List<StatementModel> listOfStatement){
        this.listOfStatement = listOfStatement;
        notifyDataSetChanged();
    }
}

class ViewHolder extends RecyclerView.ViewHolder{

    private final TransactionHistoryItemBinding binding;

    ViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    void bind(StatementModel statement){
        binding.setStatement(statement);
    }
}
