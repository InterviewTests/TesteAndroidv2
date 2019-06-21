package com.resource.bankapplication.ui.entry;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.resource.bankapplication.R;
import com.resource.bankapplication.domain.Spent;
import com.resource.bankapplication.domain.UserAccount;
import com.resource.bankapplication.ui.entry.adapter.AdapterCardEntry;
import com.resource.bankapplication.ui.login.BankLoginActivity;
import com.resource.bankapplication.util.CoinUtil;

import java.util.List;
import java.util.regex.*;

public class BankEntryActivity extends AppCompatActivity implements BankEntryContract.View {

    private BankEntryContract.Presenter presenter;
    private TextView textNameCustomer;
    private TextView textEntryAccount;
    private TextView textEntryBalance;
    private ImageView imageEntryLogout;
    private RecyclerView recyclerEntry;
    private UserAccount userAccount;
    private ProgressBar progressEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_entry);
        loadUi();
        loadExtras();
        presenter = new BankEntryPresenter(this);
        presenter.loadList(userAccount.getId());
    }
    private void loadUi() {
        textNameCustomer = findViewById(R.id.text_name_customer);
        textEntryAccount = findViewById(R.id.text_entry_account);
        textEntryBalance = findViewById(R.id.text_entry_balance);
        imageEntryLogout = findViewById(R.id.image_entry_logout);
        recyclerEntry = findViewById(R.id.recycler_entry);
        progressEntry = findViewById(R.id.progress_entry);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadExtras() {
        if(getIntent().hasExtra(BankLoginActivity.USER_ACCOUNT)) {
            userAccount = (UserAccount) getIntent()
                    .getSerializableExtra(BankLoginActivity.USER_ACCOUNT);
            textNameCustomer.setText(userAccount.getName());
            textEntryAccount.setText(String.format("%s / %s",
                    userAccount.getBankAccount(),
                    userAccount.getAgency().replaceAll(
                            "([0-9]{2})([0-9]{6})([0-9])", "$1.$2-$3")));
            textEntryBalance.setText(CoinUtil.formatReal(userAccount.getBalance()));
        }
    }

    @Override
    public void listSpent(List<Spent> value) {
        recyclerEntry.setAdapter(new AdapterCardEntry(this, value));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean show) {
        int shortAnimTime = getResources ().getInteger (android.R.integer.config_shortAnimTime);

        recyclerEntry.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
        recyclerEntry.animate ().setDuration (shortAnimTime).alpha (
                show ? 0 : 1).setListener (new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                recyclerEntry.setVisibility (show ? View.INVISIBLE : View.VISIBLE);
            }
        });

        progressEntry.setVisibility (show ? View.VISIBLE : View.GONE);
        progressEntry.animate ().setDuration (shortAnimTime).alpha (
                show ? 1 : 0).setListener (new AnimatorListenerAdapter () {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressEntry.setVisibility (show ? View.VISIBLE : View.GONE);
            }
        });
    }
}
