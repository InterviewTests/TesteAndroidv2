package com.example.santandertestebank.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.PaymentsStatements;

import java.text.NumberFormat;
import java.util.List;

public class AdapterBankPayments extends RecyclerView.Adapter<AdapterBankPayments.MyViewHolder> {

    private List<PaymentsStatements> listPayments;

    public AdapterBankPayments(List<PaymentsStatements> listPayments) {
        this.listPayments = listPayments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from (viewGroup.getContext ())
                .inflate (R.layout.adapter_payments, viewGroup, false);

        return new MyViewHolder (v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        PaymentsStatements payment = listPayments.get (i);

        myViewHolder.textViewPaymentTitle.setText (payment.getTitle ());
        myViewHolder.textViewPaymentDescription.setText (payment.getDescription ());
        myViewHolder.textViewPaymentDate.setText (
                payment.getDate ().substring (8, 10) + "/" +
                        payment.getDate ().substring (5, 7) + "/" +
                        payment.getDate ().substring (0, 4));
        myViewHolder.textViewPaymentValue.setText (NumberFormat.getCurrencyInstance ().format
                (Double.valueOf (payment.getValue ())));
    }

    @Override
    public int getItemCount() {
        if (listPayments != null) {
            return listPayments.size ();
        } else {
            return 0;
        }
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