package com.avanade.testesantander2.homeScreen;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.avanade.testesantander2.R;
import com.avanade.testesantander2.databinding.ItemCurrencyBinding;

import java.util.List;

class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder> {

    public static final String TAG = CurrencyAdapter.class.getName();
    private List<StatementModel> listaDeStatements;

    // Constructos
    public CurrencyAdapter(List<StatementModel> listaDeStatements) {
        Log.d(TAG, "constructor");
        this.listaDeStatements = listaDeStatements;
    }

    // Create Holders PARENT inside --- OK
    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreate");
        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        ItemCurrencyBinding statementLinhaBinding = DataBindingUtil
                .inflate(mLayoutInflater, R.layout.item_currency, parent, false);
        return new CurrencyHolder(statementLinhaBinding);
    }

    // BIND VIEW HOLDER
    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder holder, int position) {
        StatementModel statement = listaDeStatements.get(position);
        holder.bind(statement);
    }

    // COUNT
    @Override
    public int getItemCount() {
        return listaDeStatements.size();
    }

    // VIEW HOLDER - each ITEM-VIEW --- OK
    public static class CurrencyHolder extends RecyclerView.ViewHolder {
        private final ItemCurrencyBinding itemBinding;

        public CurrencyHolder(ItemCurrencyBinding itemBinding) {
            super(itemBinding.getRoot());
            Log.d(TAG, "view holder construct");
            this.itemBinding = itemBinding;
        }

        public void bind(StatementModel statementModel) {
            Log.d(TAG, "view holder bind");
            itemBinding.setStatement(statementModel);
            itemBinding.executePendingBindings();
        }
    }

}
