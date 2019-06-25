package com.example.santandertestebank.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.PaymentsStatmentList;

import java.util.List;

public class AdapterBankPayments extends RecyclerView.Adapter<AdapterBankPayments.MyViewHolder> {

    private List<PaymentsStatmentList> listPayments;

    public AdapterBankPayments(List<PaymentsStatmentList> list) {
        this.listPayments = list;
    }

    private Context context;

    public AdapterBankPayments(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View listItem = LayoutInflater.from (viewGroup.getContext ())
                .inflate (R.layout.adapter_payments, viewGroup, false);

        return new MyViewHolder (listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        PaymentsStatmentList payment = listPayments.get (i);
        myViewHolder.textViewPaymentTitle.setText (payment.getTitle ());
        myViewHolder.textViewPaymentDescription.setText (payment.getDescription ());
        myViewHolder.textViewPaymentDate.setText (payment.getDate ());
        myViewHolder.textViewPaymentValue.setText (String.valueOf (payment.getValue ()));
    }

    @Override
    public int getItemCount() {
        return listPayments.size ();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPaymentTitle;
        private TextView textViewPaymentDescription;
        private TextView textViewPaymentDate;
        private TextView textViewPaymentValue;


        public MyViewHolder(@NonNull View itemView) {
            super (itemView);

            textViewPaymentTitle = itemView.findViewById (R.id.text_view_payment_title);
            textViewPaymentDescription = itemView.findViewById (R.id.text_view_payment_description);
            textViewPaymentDate = itemView.findViewById (R.id.text_view_payment_date);
            textViewPaymentValue = itemView.findViewById (R.id.text_view_payment_value);
        }
    }

}