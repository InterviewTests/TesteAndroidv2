package com.resource.bankapplication.ui.entry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resource.bankapplication.R;
import com.resource.bankapplication.domain.Spent;
import com.resource.bankapplication.util.CoinUtil;

import java.util.List;

public class AdapterCardEntry extends RecyclerView.Adapter<AdapterCardEntry.AdapterCardEntryViewHolder> {

    private Context context;
    private List<Spent> spentList;

    public AdapterCardEntry(Context context, List<Spent> spentList) {
        this.context = context;
        this.spentList = spentList;
    }

    @NonNull
    @Override
    public AdapterCardEntryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View viewCreate = LayoutInflater.from(context)
                .inflate(R.layout.card_entry, viewGroup, false);
        return new AdapterCardEntryViewHolder(viewCreate);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCardEntryViewHolder holder, int position) {
        if(spentList != null){
            Spent spent = spentList.get(position);
            holder.setItem(spent);
        }
    }

    @Override
    public int getItemCount() {
        if(spentList != null)
            return spentList.size();
        return 0;
    }

    public class AdapterCardEntryViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTypeTransaction;
        private final TextView textDate;
        private final TextView textDescription;
        private final TextView textValue;

        public AdapterCardEntryViewHolder(@NonNull View itemView) {
            super(itemView);
            textTypeTransaction = itemView.findViewById(R.id.text_type_transaction);
            textDate = itemView.findViewById(R.id.text_date);
            textDescription = itemView.findViewById(R.id.text_description);
            textValue = itemView.findViewById(R.id.text_value);
        }

        public void setItem(Spent spent) {
            textTypeTransaction.setText(spent.getTypeTransaction());
            textDate.setText(spent.getDate());
            textDescription.setText(spent.getDescription());
            textValue.setText(CoinUtil.formatReal(spent.getValue()));
        }

    }
}
