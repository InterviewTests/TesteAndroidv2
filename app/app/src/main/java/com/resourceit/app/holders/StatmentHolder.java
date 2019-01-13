package com.resourceit.app.holders;

import android.view.View;
import android.widget.TextView;

import com.resourceit.app.R;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatmentHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.title) public TextView title;
    @BindView(R.id.date) public TextView date;
    @BindView(R.id.desc) public TextView desc;
    @BindView(R.id.value) public TextView value;
    public StatmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
