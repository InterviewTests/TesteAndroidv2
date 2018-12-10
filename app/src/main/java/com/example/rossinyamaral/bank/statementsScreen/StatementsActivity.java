package com.example.rossinyamaral.bank.statementsScreen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rossinyamaral.bank.R;
import com.example.rossinyamaral.bank.model.StatementModel;
import com.example.rossinyamaral.bank.model.UserAccountModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


interface StatementsActivityInput {
    public void displayStatementsData(StatementsViewModel viewModel);
}


public class StatementsActivity extends AppCompatActivity
        implements StatementsActivityInput {

    public static String TAG = StatementsActivity.class.getSimpleName();
    StatementsInteractorInput output;
    StatementsRouter router;


    UserAccountModel userAccountModel;
    ArrayList<StatementModel> statementsList;

    TextView nameTextView;
    TextView accountTextView;
    TextView balanceTextView;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //do the setup
        setContentView(R.layout.activity_statements);

        StatementsConfigurator.INSTANCE.configure(this);

        userAccountModel = (UserAccountModel) getIntent().getParcelableExtra("userAccount");
        if (userAccountModel == null) {
            Toast.makeText(this, "Ops! Ocorreu um erro...", Toast.LENGTH_LONG).show();
            finish();
            return;
        }


        nameTextView = findViewById(R.id.nameEditText);
        accountTextView = findViewById(R.id.accountTextView);
        balanceTextView = findViewById(R.id.balanceTextView);
        recyclerView = findViewById(R.id.recyclerView);

        nameTextView.setText(userAccountModel.getName());
        accountTextView.setText(String.format("%s / %s",
                userAccountModel.getBankAccount(), userAccountModel.getAgency()));
        balanceTextView.setText(getMoneyFormat(userAccountModel.getBalance()));

        StatementsRequest aStatementsRequest = new StatementsRequest();
        //populate the request
        aStatementsRequest.userId = userAccountModel.getUserId();

        output.fetchStatementsData(aStatementsRequest);
        // Do other work
    }


    @Override
    public void displayStatementsData(StatementsViewModel viewModel) {
        Log.e(TAG, "displayStatementsData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
        setStatementsLisView(viewModel.statements);
    }

    private void setStatementsLisView(List<StatementModel> statements) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StatementListAdapter(statements));
        Log.d(TAG, "Configured RecyclerView");
    }

    public String getMoneyFormat(double value) {
        return String.format(new Locale("PT", "br"),"R$%.2f", value);
    }


    private class StatementListAdapter extends RecyclerView.Adapter<StatementListAdapter.ViewHolder> {

        private LayoutInflater layoutInflater;
        List<StatementModel> statements;

        StatementListAdapter(List<StatementModel> statements) {
            this.layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            this.statements = statements;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View layoutView = layoutInflater.inflate(R.layout.item_statement, parent, false);
            ViewHolder viewHolder = new ViewHolder(layoutView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            viewHolder.titleTextView.setText(statements.get(position).title);
            viewHolder.descTextView.setText(statements.get(position).desc);
            viewHolder.dateTextView.setText(statements.get(position).date);
            viewHolder.valueTextView.setText(getMoneyFormat(statements.get(position).value));
        }

        @Override
        public int getItemCount() {
            return statements != null ? statements.size() : 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView titleTextView;
            TextView descTextView;
            TextView dateTextView;
            TextView valueTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
                descTextView = (TextView) itemView.findViewById(R.id.descTextView);
                dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
                valueTextView = (TextView) itemView.findViewById(R.id.valueTextView);
            }
        }
    }
}
