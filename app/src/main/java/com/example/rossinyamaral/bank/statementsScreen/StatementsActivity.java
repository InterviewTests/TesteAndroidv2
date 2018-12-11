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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rossinyamaral.bank.BankApplication;
import com.example.rossinyamaral.bank.R;
import com.example.rossinyamaral.bank.model.StatementModel;
import com.example.rossinyamaral.bank.model.UserAccountModel;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    TextView nameTextView;
    TextView accountTextView;
    TextView balanceTextView;
    ImageView logoutImageView;

    RecyclerView recyclerView;
    private Locale LOCALE = new Locale("PT", "br");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //do the setup
        setContentView(R.layout.activity_statements);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        BankApplication.getInstance().setStatusBarColor(this, R.color.colorButton);

        StatementsConfigurator.INSTANCE.configure(this);

        userAccountModel = getIntent().getParcelableExtra("userAccount");
        if (userAccountModel == null) {
            Toast.makeText(this, "Ops! Ocorreu um erro...", Toast.LENGTH_LONG).show();
            finish();
            return;
        }


        nameTextView = findViewById(R.id.nameEditText);
        accountTextView = findViewById(R.id.accountTextView);
        balanceTextView = findViewById(R.id.balanceTextView);
        logoutImageView = findViewById(R.id.logoutImageView);
        recyclerView = findViewById(R.id.recyclerView);

        nameTextView.setText(userAccountModel.getName());
        accountTextView.setText(String.format("%s / %s", userAccountModel.getBankAccount(),
                getFormattedAgency(userAccountModel.getAgency())));
        balanceTextView.setText(getFormattedMoney(userAccountModel.getBalance()));

        logoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

    public String getFormattedAgency(String agency) {
        if (agency != null && agency.length() == 9) {
            StringBuilder builder = new StringBuilder(agency);
            builder.insert(8, "-");
            builder.insert(2, ".");
            agency = builder.toString();
        }
        return agency;
    }

    public String getFormattedMoney(double value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(LOCALE);
        return numberFormat.format(value);
    }

    public String getFormatedDate(String dateString) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", LOCALE);
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", LOCALE);
            Date date = format1.parse(dateString);
            return format2.format(date);
        } catch (Exception e) {
            return "";
        }
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

            int resource = viewType == 0 ? R.layout.item_recents : R.layout.item_statement;
            View layoutView = layoutInflater.inflate(resource, parent, false);
            ViewHolder viewHolder = new ViewHolder(layoutView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            int pos = position - 1;
            if (position > 0) {
                viewHolder.titleTextView.setText(statements.get(pos).title);
                viewHolder.descTextView.setText(statements.get(pos).desc);
                viewHolder.dateTextView.setText(getFormatedDate(statements.get(pos).date));
                viewHolder.valueTextView.setText(getFormattedMoney(statements.get(pos).value));
            }
        }

        @Override
        public int getItemCount() {
            return statements != null ? statements.size() + 1 : 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
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
