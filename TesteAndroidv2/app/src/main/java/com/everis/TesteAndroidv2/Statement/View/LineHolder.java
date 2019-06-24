package com.everis.TesteAndroidv2.Statement.View;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.everis.TesteAndroidv2.R;

class LineHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView description;
    TextView date;
    TextView value;

    LineHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_transaction_title);
        description = itemView.findViewById(R.id.tv_transaction_description);
        date = itemView.findViewById(R.id.tv_transaction_date);
        value = itemView.findViewById(R.id.tv_transaction_value);
    }
}
