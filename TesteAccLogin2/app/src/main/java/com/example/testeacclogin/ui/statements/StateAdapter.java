package com.example.testeacclogin.ui.statements;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testeacclogin.R;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<StateItem> mExampleList;

    public StateAdapter(Context context, ArrayList<StateItem> exampleList){
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.state_item, parent, false);
            return new ExampleViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        StateItem currentItem = mExampleList.get(position);

        String estTitle = currentItem.getmTitle();
        String estDesc = currentItem.getmDesc();
        String estDate = currentItem.getmData();
        String estValue = currentItem.getmValue();

        holder.mTextViewTitle.setText(estTitle);
        holder.mTextViewDesc.setText(estDesc);
        holder.mTextViewData.setText(estDate);
        holder.mTextViewValue.setText(estValue);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewTitle;
        public TextView mTextViewDesc;
        public TextView mTextViewData;
        public TextView mTextViewValue;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.text_view_title);
            mTextViewDesc = itemView.findViewById(R.id.text_view_desc);
            mTextViewData = itemView.findViewById(R.id.text_view_date);
            mTextViewValue = itemView.findViewById(R.id.text_view_value);
        }
    }
}
