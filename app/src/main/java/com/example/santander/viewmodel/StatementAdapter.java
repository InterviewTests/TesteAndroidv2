package com.example.santander.viewmodel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.santander.R;
import com.example.santander.model.statementVO;

import java.util.ArrayList;
import java.util.List;

public class StatementAdapter extends
        RecyclerView.Adapter<StatementAdapter.ViewHolder> {

    private ArrayList<statementVO> mStatementList;

    public StatementAdapter(List<statementVO> statementVO) {
        mStatementList = new ArrayList<>();
        mStatementList.addAll(statementVO);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statement, parent, false);

        return new StatementAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementAdapter.ViewHolder viewHolder, int position) {
        statementVO statementVO = mStatementList.get(position);

        String title = statementVO.getTitle();
        viewHolder.statementTitle.setText(title);

        String date = statementVO.getDate();
        viewHolder.statementDate.setText(date);

        String desc = statementVO.getDesc();
        viewHolder.statementDesc.setText(desc);

        Double value = statementVO.getValue();
        viewHolder.statementValue.setText(String.valueOf(value));

    }

    @Override
    public int getItemCount() {
        return mStatementList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ;
        TextView statementTitle;
        TextView statementDate;
        TextView statementDesc;
        TextView statementValue;

        ViewHolder(View itemView) {
            super(itemView);

            statementTitle = itemView.findViewById(R.id.tv_item_atividades_titulo);
            statementDate = itemView.findViewById(R.id.tv_item_atividades_data);
            statementDesc = itemView.findViewById(R.id.tv_item_atividades_descricao);
            statementValue = itemView.findViewById(R.id.tv_item_atividades_valor);
        }
    }
}