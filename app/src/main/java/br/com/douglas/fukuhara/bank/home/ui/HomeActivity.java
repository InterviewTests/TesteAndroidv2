package br.com.douglas.fukuhara.bank.home.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.douglas.fukuhara.bank.R;
import br.com.douglas.fukuhara.bank.home.Contract;
import br.com.douglas.fukuhara.bank.home.adapter.RecentListAdapter;
import br.com.douglas.fukuhara.bank.home.configurator.HomeConfigurator;
import br.com.douglas.fukuhara.bank.home.router.HomeRouter;
import br.com.douglas.fukuhara.bank.network.vo.StatementListVo;
import br.com.douglas.fukuhara.bank.network.vo.UserAccount;

import static br.com.douglas.fukuhara.bank.utils.LoginUtils.getUserAccountFromBundle;

public class HomeActivity extends AppCompatActivity implements Contract.HomeActivityInput {

    private TextView mUsername;
    private TextView mAccount;
    private TextView mBalance;
    private ImageView mLogout;
    private TextView mRecentLabel;
    private TextView mRecentNoDataAvailable;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    // Android Clean Code interfaces
    private Contract.HomeInteractorInput mOutput;
    private HomeRouter mRouter;

    private RecentListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UserAccount userAccount = getUserAccountFromBundle(getIntent());

        setViews();
        setRecyclerView();

        // Setting the Configurator
        HomeConfigurator.configure(this);

        mOutput.setHomeHeader(userAccount);
        mOutput.fetchUserData();
    }

    private void setRecyclerView() {
        mAdapter = new RecentListAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setViews() {
        mUsername = findViewById(R.id.home_tv_username);
        mAccount = findViewById(R.id.home_tv_account);
        mBalance = findViewById(R.id.home_tv_balance);
        mLogout = findViewById(R.id.home_btn_logout);
        mRecentLabel = findViewById(R.id.home_tv_recents_label);
        mRecentNoDataAvailable = findViewById(R.id.home_tv_no_content);
        mRecyclerView = findViewById(R.id.home_rv_recents_list);
        mProgressBar = findViewById(R.id.home_pg_load_rv);

        mLogout.setOnClickListener(this::onLogoutClicked);
    }

    private void onLogoutClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.home_logout_message);
        builder.setNegativeButton(R.string.home_logout_cancel, (dialogInterface, i) -> {} );
        builder.setPositiveButton(R.string.home_logout_proceed, mRouter::onLogoutConfirmation );
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public void setHeaderInformation(String username, String account, String balance) {
        mUsername.setText(username);
        mAccount.setText(account);
        mBalance.setText(balance);
    }

    @Override
    public void setRecentList(List<StatementListVo.StatementItem> statementList) {
        mAdapter.updateDataSetContent(statementList);
    }

    @Override
    public void showContentLoader() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentLoader() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUserDataContainer() {
        mRecentLabel.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUserDataContainer() {
        mRecentLabel.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoDataAvailable() {
        mRecentNoDataAvailable.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataAvailable() {
        mRecentNoDataAvailable.setVisibility(View.GONE);
    }

    @Override
    public void notifyResourceErrorToUser(int stringRes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(stringRes);
        builder.setPositiveButton(R.string.login_dialog_error_ok_btn, (dialogInterface, i) -> {});
        builder.create().show();
    }

    @Override
    public void notifyErrorToUser(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.login_dialog_error_ok_btn, (dialogInterface, i) -> {});
        builder.create().show();
    }

    public Contract.HomeInteractorInput getOutput() {
        return mOutput;
    }

    public void setOutput(Contract.HomeInteractorInput output) {
        mOutput = output;
    }

    public HomeRouter getRouter() {
        return mRouter;
    }

    public void setRouter(HomeRouter router) {
        mRouter = router;
    }

    @Override
    protected void onDestroy() {
        mOutput.disposeAll();
        super.onDestroy();
    }
}
