package com.example.androidcodingtest.statements;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidcodingtest.R;
import com.example.androidcodingtest.models.Statement;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder> {

    private List<Statement> statementList;
    private Context context;
    private ViewHolder mHolder;

    public StatementAdapter(List<Statement> items) {
        statementList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statement_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        mHolder = holder;
        holder.mItem = statementList.get(position);

        holder.mStatementTitle.setText(holder.mItem.getTitle());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(holder.mItem.getDate());
        } catch (ParseException e) {
            // handle exception here !
        }
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String formattedDate = dateFormat.format(date);
        holder.mStatementDate.setText(formattedDate);

        holder.mStatementDescription.setText(holder.mItem.getDesc());

        NumberFormat format = NumberFormat.getCurrencyInstance();
        holder.mStatementValue.setText(format.format(holder.mItem.getValue()));
    }

    public void setValues(List<Statement> statementList){
        this.statementList = statementList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return statementList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final Activity mActivity;

        public final TextView mStatementTitle;
        public final TextView mStatementDate;
        public final TextView mStatementDescription;
        public final TextView mStatementValue;

        public Statement mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mActivity = (Activity)view.getContext();

            mStatementTitle = view.findViewById(R.id.statement_title);
            mStatementDate = view.findViewById(R.id.statement_date);
            mStatementDescription = view.findViewById(R.id.statement_description);
            mStatementValue = view.findViewById(R.id.statement_value);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mStatementTitle.getText() + "'";
        }
    }
}