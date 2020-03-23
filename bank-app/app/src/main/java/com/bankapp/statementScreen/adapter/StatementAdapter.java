package com.bankapp.statementScreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bankapp.R;
import com.bankapp.statementScreen.StatementActivity;
import com.bankapp.statementScreen.StatementModel;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementViewHolder>{

    public static String TAG = StatementActivity.class.getSimpleName();

    private List<StatementModel> listStatement;
    private Context context;

    public StatementAdapter(List<StatementModel> listStatement) {
        this.listStatement = listStatement;
    }
    @Override
    public StatementAdapter.StatementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_statement, parent, false);
        StatementAdapter.StatementViewHolder viewHolder = new StatementAdapter.StatementViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final StatementAdapter.StatementViewHolder holder, int position) {

        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("R$#,##0.00;-R$#,##0.00");

        final StatementModel mItem = listStatement.get(position);
        holder.itemType.setText(mItem.title);
        holder.itemDescription.setText(mItem.desc);
        holder.itemValue.setText(df.format(new BigDecimal(mItem.value)));
        holder.itemDate.setText(getDate(mItem.date));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @Override
    public int getItemCount() {
        return listStatement.size();
    }

    public static class StatementViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView itemType;
        private AppCompatTextView itemDescription;
        private AppCompatTextView itemValue;
        private AppCompatTextView itemDate;

        public StatementViewHolder(View itemView) {
            super(itemView);
            itemType = (AppCompatTextView) itemView.findViewById(R.id.itemType);
            itemDescription = (AppCompatTextView) itemView.findViewById(R.id.itemDescription);
            itemValue = (AppCompatTextView) itemView.findViewById(R.id.itemValue);
            itemDate = (AppCompatTextView) itemView.findViewById(R.id.itemDate);
        }
    }

    public String getDate(String strDate)  {

        String newDate = strDate;

        try {
            DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date date = dateFormat.parse(strDate);
            c.setTime(date);

            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            newDate = newDateFormat.format(c.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(context, context.getString(R.string.error_loading_user_details), Toast.LENGTH_LONG).show();
        }

        return newDate;
    }
}