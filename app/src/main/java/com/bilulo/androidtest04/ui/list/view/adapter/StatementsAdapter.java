package com.bilulo.androidtest04.ui.list.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bilulo.androidtest04.R;
import com.bilulo.androidtest04.data.model.StatementModel;
import com.bilulo.androidtest04.utils.FormatUtil;
import com.bilulo.androidtest04.utils.ValidationUtil;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.List;

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.StatementsViewHolder> {

    private List<StatementModel> mData;
    private Context mContext;

    @NonNull
    @Override
    public StatementsAdapter.StatementsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index) {
        mContext = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new StatementsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementsViewHolder statementsViewHolder, int index) {
        StatementModel statementModel = mData.get(index);
        if (statementModel != null) {
            String title = statementModel.getTitle();
            String desc = statementModel.getDesc();
            String date = statementModel.getDate();
            BigDecimal value = statementModel.getValue();

            String valueFormatted = FormatUtil.formatCurrency(value);
            String formattedDate = FormatUtil.formatDateFromString("dd/MM/yyyy", "yyyy-MM-dd", date);

            if (ValidationUtil.isValidString(title))
                statementsViewHolder.tvType.setText(title);
            if (ValidationUtil.isValidString(desc))
                statementsViewHolder.tvDescription.setText(desc);
            if (ValidationUtil.isValidString(valueFormatted))
                statementsViewHolder.tvValue.setText(valueFormatted);
            if (ValidationUtil.isValidString(formattedDate))
                statementsViewHolder.tvDate.setText(formattedDate);
        }
    }


    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        else return mData.size();
    }

    public void setData(List<StatementModel> statementModelList) {
        mData = statementModelList;
        notifyDataSetChanged();
    }

    class StatementsViewHolder extends RecyclerView.ViewHolder {
        TextView tvType;
        TextView tvDescription;
        TextView tvValue;
        TextView tvDate;

        StatementsViewHolder(View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_statement_type);
            tvDescription = itemView.findViewById(R.id.tv_statement_description);
            tvValue = itemView.findViewById(R.id.tv_statement_value);
            tvDate = itemView.findViewById(R.id.tv_statement_date);
        }
    }
}
