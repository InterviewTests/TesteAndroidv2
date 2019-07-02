package com.example.santanderapplication.ui.transactions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.santanderapplication.R;
import com.example.santanderapplication.data.model.StatementsModel;

import java.util.List;

public class AdapterBankCurrency extends RecyclerView.Adapter<AdapterBankCurrency.MyViewHolder> {

    private Context context;
    private List<StatementsModel> statementsModelList;

    public AdapterBankCurrency (Context context, List<StatementsModel> statementsModelList){
        this.context = context;
        this.statementsModelList=statementsModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemList = LayoutInflater.from( parent.getContext() ).
                inflate( R.layout.card_adapter_bank_currency,parent,false );

        return new MyViewHolder( itemList );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        StatementsModel statementsModel = statementsModelList.get( i );
        holder.textViewDateCurrency.setText(statementsModel.getDate()+"");
        holder.textViewTitleCurrency.setText( statementsModel.getTitle());
        holder.textViewDescCurrency.setText( statementsModel.getDesc());
        holder.textViewValueCurrency.setText(statementsModel.getValue()+"");
    }

    @Override
    public int getItemCount() {
        return statementsModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitleCurrency;
        private TextView textViewDateCurrency;
        private TextView textViewDescCurrency;
        private TextView textViewValueCurrency;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            textViewDateCurrency = itemView.findViewById( R.id.text_date_bank_currency );
            textViewDescCurrency = itemView.findViewById( R.id.text_desc_bank_currency );
            textViewTitleCurrency = itemView.findViewById( R.id.text_title_bank_currency );
            textViewValueCurrency = itemView.findViewById( R.id.text_value_bank_currency );
        }
    }
}
