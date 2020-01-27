package com.msbm.bank.accountDetailScreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.msbm.bank.BaseActivity;
import com.msbm.bank.R;
import com.msbm.bank.entities.Statement;
import com.msbm.bank.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

interface AccountDetailActivityInput {
    void displayStatementData(AccountDetailViewModel viewModel);
}

public class AccountDetailActivity extends BaseActivity implements AccountDetailActivityInput {

    public List<Statement> statements;

    protected AccountDetailInteractorInput accountDetailInteractorInput;

    private TextView txvName;
    private TextView txvAccountAgency;
    private TextView txvTotal;
    private RecyclerView rvwStatement;

    private StatementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        getSupportActionBar().hide();
        bindViews();

        AccountDetailConfigurator.INSTANCE.configure(this);
        fetchStatementData();
    }

    @Override
    public void displayStatementData(AccountDetailViewModel viewModel) {
        statements = viewModel.statements;
        adapter.setData(statements);
    }

    public void fetchStatementData() {
        StatementRequest request = new StatementRequest();
        request.userId = "1";

        accountDetailInteractorInput.fetchStatementData(request);
    }

    private void bindViews() {
        txvName = findViewById(R.id.idName);
        txvAccountAgency = findViewById(R.id.idAccountNumberAgency);
        txvTotal = findViewById(R.id.idTotal);

        rvwStatement = findViewById(R.id.rvwStatement);
        adapter = new StatementAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvwStatement.setLayoutManager(linearLayoutManager);
        rvwStatement.setAdapter(adapter);
    }

    private class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.StatementVH> {

        public List<Statement> data;
        LayoutInflater inflater;

        StatementAdapter() {
            data = new ArrayList<>();
        }

        @NonNull
        @Override
        public StatementVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = inflater.inflate(R.layout.item_statement, parent, false);

            return new StatementVH(convertView);
        }

        @Override
        public void onBindViewHolder(@NonNull StatementVH holder, int position) {
            holder.txvTitle.setText(statements.get(position).getTitle());
            holder.txvDate.setText(statements.get(position).getDate());
            holder.txvDescription.setText(statements.get(position).getDesc());
            holder.txvValue.setText(StringUtil.getCurrencyValue("R$", statements.get(position).getValue()));
            if (statements.get(position).getValue() >= 0) {
                holder.txvValue.setTextColor(getResources().getColor(R.color.colorGreen));
            } else {
                holder.txvValue.setTextColor(getResources().getColor(R.color.colorRed));
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setData(List<Statement> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        class StatementVH extends RecyclerView.ViewHolder {

            public TextView txvTitle;
            public TextView txvDate;
            public TextView txvDescription;
            public TextView txvValue;

            public StatementVH(@NonNull View itemView) {
                super(itemView);
                txvTitle = itemView.findViewById(R.id.idTitle);
                txvDate = itemView.findViewById(R.id.idDate);
                txvDescription = itemView.findViewById(R.id.idDescription);
                txvValue = itemView.findViewById(R.id.idValue);
            }
        }
    }
}
