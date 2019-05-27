package com.caique.everis.testeandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caique.everis.testeandroid.R;
import com.caique.everis.testeandroid.model.AccountInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountListViewHolder> {
        private List<AccountInfo> mData;

    public static class AccountListViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView date;
        public TextView value;
        public AccountListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.payment);
            desc = itemView.findViewById(R.id.payment_type);
            date = itemView.findViewById(R.id.date);
            value = itemView.findViewById(R.id.value);
        }
    }

    public AccountListAdapter(List<AccountInfo> accountInfos) {
        this.mData = accountInfos;
    }

    @NonNull
    @Override
    public AccountListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View accountView = layoutInflater.inflate(R.layout.recyclerview_item, viewGroup, false);

        return new AccountListViewHolder(accountView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountListViewHolder accountListViewHolder, int i) {
        AccountInfo accountInfo = mData.get(i);

        TextView title = accountListViewHolder.title;
        title.setText(accountInfo.getTitle());
        TextView desc = accountListViewHolder.desc;
        desc.setText(accountInfo.getDesc());
        TextView date = accountListViewHolder.date;

        String dateFormatted = formatDate(accountInfo);

        date.setText(dateFormatted);
        TextView value = accountListViewHolder.value;
        value.setText("R$ " + accountInfo.getValue().toString());


    }

    private String formatDate(AccountInfo accountInfo) {
        String strCurrentDate = accountInfo.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(newDate);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
