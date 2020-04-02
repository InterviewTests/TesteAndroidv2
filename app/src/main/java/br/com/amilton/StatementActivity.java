package br.com.amilton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;

import br.com.amilton.adapter.StatementAdapter;
import br.com.amilton.databinding.ActivityStatementBinding;
import br.com.amilton.model.UserAccount;
import br.com.amilton.viewmodel.StatementViewModel;

public class StatementActivity extends AppCompatActivity {

    private static final String USER_ACCOUNT = "user_account";
    private static final String ACCOUNT_MASK = "NN.NNNNNN-N";

    private final StatementAdapter mAdapter = new StatementAdapter();

    private ActivityStatementBinding activityStatementBinding;

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
        activityStatementBinding.setStatementViewModel(new ViewModelProvider(this).get(StatementViewModel.class));
        activityStatementBinding.setAccountSimpleMaskFormatter(new SimpleMaskFormatter(ACCOUNT_MASK));

        activityStatementBinding.ibLogout.setOnClickListener(v -> StatementActivity.this.finish());
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(USER_ACCOUNT)) {
            UserAccount localUserAccount = (UserAccount) bundle.getSerializable(USER_ACCOUNT);
            activityStatementBinding.getStatementViewModel().setUserAccount(localUserAccount);
        } else {
            throw new IllegalArgumentException("Account not found!");
        }

        activityStatementBinding.recyclerview.setHasFixedSize(true);
        activityStatementBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        activityStatementBinding.recyclerview.setAdapter(mAdapter);

        activityStatementBinding.swipeRefreshRecyclerView.setOnRefreshListener(() -> subscribeUi(activityStatementBinding.getStatementViewModel().getUserAccount().getValue().getUserId()));
        subscribeUi(activityStatementBinding.getStatementViewModel().getUserAccount().getValue().getUserId());
    }

    private void subscribeUi(String userId) {
        activityStatementBinding.swipeRefreshRecyclerView.setRefreshing(true);
        activityStatementBinding.getStatementViewModel().getStatementsMutableLiveData(userId).observe(StatementActivity.this, statements -> {
            mAdapter.setList(statements);
            activityStatementBinding.swipeRefreshRecyclerView.setRefreshing(false);
        });
    }
}
