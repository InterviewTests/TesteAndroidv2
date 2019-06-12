package br.com.douglas.fukuhara.bank.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.douglas.fukuhara.bank.R;
import br.com.douglas.fukuhara.bank.network.vo.StatementListVo;

import static br.com.douglas.fukuhara.bank.utils.FormatterUtils.formatDateToBrazilian;
import static br.com.douglas.fukuhara.bank.utils.FormatterUtils.includeCurrencyInValue;

public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.RecentItemViewHolder> {

    private List<StatementListVo.StatementItem> mList;

    public RecentListAdapter(List<StatementListVo.StatementItem> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecentItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecentItemViewHolder holder, int position) {
        if (getItemCount() > 0) {
            holder.onBind(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void updateDataSetContent(List<StatementListVo.StatementItem> statementList) {
        mList = statementList;
        notifyDataSetChanged();
    }

    class RecentItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView recentTitle;
        private final TextView recentDate;
        private final TextView recentDesc;
        private final TextView recentValue;

        RecentItemViewHolder(@NonNull View itemView) {
            super(itemView);

            recentTitle = itemView.findViewById(R.id.recent_tv_title);
            recentDate = itemView.findViewById(R.id.recent_tv_date);
            recentDesc = itemView.findViewById(R.id.recent_tv_desc);
            recentValue = itemView.findViewById(R.id.recent_tv_value);
        }

        void onBind(StatementListVo.StatementItem recentItem) {

            String formattedValue = includeCurrencyInValue(recentItem.getValue());
            String formattedDate = formatDateToBrazilian(recentItem.getDate());
            recentTitle.setText(recentItem.getTitle());
            recentDesc.setText(recentItem.getDesc());
            recentValue.setText(formattedValue);
            recentDate.setText(formattedDate);
        }
    }
}
