package com.ivan.bankapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ivan.bankapp.R;
import com.ivan.bankapp.view.fragment.FragmentBills;

import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private ArrayList<FragmentBills> billsList;
    private Context context;
    private TextView billDescription, billDate, billValue, billTitle;


    public BillAdapter(@NonNull ArrayList<FragmentBills> list) {

        billsList = new ArrayList<>();

        for(int i = 0; i<list.size(); i++) {
            billsList.add(list.get(i));
        }
    }

    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bills, parent, false);

        billDescription = view.findViewById(R.id.billDescription);
        billDate = view.findViewById(R.id.billDate);
        billValue = view.findViewById(R.id.billValue);
        billTitle = view.findViewById(R.id.title);

        return new BillAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FragmentBills billsCard = billsList.get(position);


        holder.billDescription.setText(String.valueOf(billsCard.getBillDescription()));
        holder.billDate.setText(String.valueOf(billsCard.getBillDate()));
        holder.billValue.setText("R$ " + (String.valueOf(billsCard.getBillValue())));
        holder.billTitle.setText(billsCard.getTitle());

    }

    @Override
    public int getItemCount() {
        return billsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView billDescription, billDate, billValue, billTitle;

        public ViewHolder(View view) {
            super(view);

            billDescription = view.findViewById(R.id.billDescription);
            billDate = view.findViewById(R.id.billDate);
            billValue = view.findViewById(R.id.billValue);
            billTitle = view.findViewById(R.id.title);

        }
    }
}