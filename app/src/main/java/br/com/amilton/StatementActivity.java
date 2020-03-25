package br.com.amilton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.amilton.adapter.StatementAdapter;
import br.com.amilton.databinding.ActivityStatementBinding;
import br.com.amilton.model.UserAccount;
import br.com.amilton.viewmodel.StatementViewModel;

public class StatementActivity extends AppCompatActivity {

    private static final String USER_ACCOUNT = "user_account";
    private static final String ACCOUNT_MASK = "NN.NNNNNN-N";

    private final StatementAdapter mAdapter = new StatementAdapter();

    private ActivityStatementBinding activityStatementBinding;
    private StatementViewModel statementViewModel;

    public static Intent newInstance(Activity activity, UserAccount userAccount) {
        Intent intent = new Intent(activity, StatementActivity.class);
        intent.putExtra(USER_ACCOUNT, userAccount);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStatementBinding = ActivityStatementBinding.inflate(getLayoutInflater());
        setContentView(activityStatementBinding.getRoot());

        statementViewModel = new ViewModelProvider(this).get(StatementViewModel.class);

        activityStatementBinding.ibLogout.setOnClickListener(v -> StatementActivity.this.finish());
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(USER_ACCOUNT)) {
            UserAccount localUserAccount = (UserAccount) bundle.getSerializable(USER_ACCOUNT);
            statementViewModel.setUserAccount(localUserAccount);
        } else {
            throw new IllegalArgumentException("Account not found!");
        }

        activityStatementBinding.recyclerview.setHasFixedSize(true);
        activityStatementBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        activityStatementBinding.recyclerview.setAdapter(mAdapter);

        statementViewModel.getUserAccount().observe(this, userAccount -> {
            activityStatementBinding.tvNameTitle.setText(userAccount.getName());
            activityStatementBinding.tvAccount.setText(String.format("%s / %s", userAccount.getBankAccount(), new SimpleMaskFormatter(ACCOUNT_MASK).format(userAccount.getAgency())));

            final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("en", "US"));
            decimalFormat.applyPattern(String.format("%s ##,##0.00", NumberFormat.getCurrencyInstance().getCurrency().getSymbol()));

            activityStatementBinding.tvBalance.setText(decimalFormat.format(userAccount.getBalance()));
            activityStatementBinding.swipeRefreshRecyclerView.setOnRefreshListener(() -> subscribeUi(userAccount.getUserId()));
            subscribeUi(userAccount.getUserId());
        });
    }

    private void subscribeUi(String userId) {
        activityStatementBinding.swipeRefreshRecyclerView.setRefreshing(true);
        statementViewModel.getStatementsMutableLiveData(userId).observe(StatementActivity.this, statements -> {
            mAdapter.setList(statements);
            activityStatementBinding.swipeRefreshRecyclerView.setRefreshing(false);
        });
    }
}
