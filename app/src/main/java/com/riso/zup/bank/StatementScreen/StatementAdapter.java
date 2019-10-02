package com.riso.zup.bank.StatementScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.riso.zup.bank.R;
import com.riso.zup.bank.helpers.Utils;
import com.riso.zup.bank.models.Statement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatementAdapter extends RecyclerView.Adapter{

    private List<Statement> listStatement;
    private ViewHolder holder;
    private Context ct;
    private Statement statementObject;

    public StatementAdapter(List<Statement> listStatement, Context context){
        this.listStatement = listStatement;
        this.ct = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ct).inflate(R.layout.item_extract_list, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        holder = (ViewHolder) viewHolder;

        statementObject = listStatement.get(position);

        holder.tvTitle.setText(statementObject.getTitle());

        String date = Utils.formatDate(statementObject.getDate());
        holder.tvDate.setText(date);

        holder.tvDesc.setText(statementObject.getDesc());

        String balance = Utils.formatValue(ct, statementObject.getValue());
        holder.tvValue.setText(balance);

        setColorView(statementObject.getValue());

    }

    @Override
    public int getItemCount() {
        return listStatement.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title_extract)
        TextView tvTitle;

        @BindView(R.id.tv_date_extract)
        TextView tvDate;

        @BindView(R.id.tv_desc_extract)
        TextView tvDesc;

        @BindView(R.id.tv_value_extract)
        TextView tvValue;

        @BindView(R.id.view_type_transaction)
        View viewType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setColorView(Double value){

        if(value > 0)
            holder.viewType.setBackgroundResource(R.color.green);
        else
            holder.viewType.setBackgroundResource(R.color.red);
    }
}
