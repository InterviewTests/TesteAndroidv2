package com.resource.bankapplication.ui.entry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.resource.bankapplication.R;
import com.resource.bankapplication.domain.Spent;
import com.resource.bankapplication.domain.UserAccount;
import com.resource.bankapplication.ui.entry.adapter.AdapterCardEntry;
import com.resource.bankapplication.ui.login.BankLoginActivity;

import java.util.List;

public class BankEntryActivity extends AppCompatActivity implements BankEntryContract.View {

    private BankEntryContract.Presenter presenter;
    private TextView textNameCustomer;
    private TextView textEntryAccount;
    private TextView textEntryBalance;
    private ImageView imageEntryLogout;
    private RecyclerView recyclerEntry;
    private UserAccount userAccount;

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
    }

    private void loadExtras() {
        if(getIntent().hasExtra(BankLoginActivity.USER_ACCOUNT)) {
            userAccount = (UserAccount) getIntent()
                    .getSerializableExtra(BankLoginActivity.USER_ACCOUNT);
            textNameCustomer.setText(userAccount.getName());
            textEntryAccount.setText(String.format("%s / %s",
                    userAccount.getAgency(), userAccount.getBankAccount()));
            textEntryBalance.setText(String.format("R$%s", String.valueOf(userAccount.getBalance())));
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
}
