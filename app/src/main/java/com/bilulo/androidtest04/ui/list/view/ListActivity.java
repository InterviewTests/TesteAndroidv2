package com.bilulo.androidtest04.ui.list.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilulo.androidtest04.R;
import com.bilulo.androidtest04.common.BaseActivity;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.ui.list.configurator.ListConfigurator;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.utils.FormatUtil;
import com.bilulo.androidtest04.utils.ValidationUtil;

public class ListActivity extends BaseActivity implements ListContract.ActivityContract, View.OnClickListener {
    public static final String EXTRA_USER_ACCOUNT = "extra-user-account";
    public ListContract.InteractorContract interactor;

    private TextView tvClientName;
    private ImageView ivLogout;
    private TextView tvAccountDescription;
    private TextView tvAccount;
    private TextView tvBalanceDescription;
    private TextView tvBalance;
    private TextView tvRecentDescription;
    private RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListConfigurator.configure(this);
        hideActionBar();
        findViews();
        loadAccountData(getUserAccountModelExtra());
    }

    private void findViews() {
        tvClientName = findViewById(R.id.tv_client_name);
        ivLogout = findViewById(R.id.iv_logout);
        tvAccount = findViewById(R.id.tv_account);
        tvBalance = findViewById(R.id.tv_balance);
        rvList = findViewById(R.id.rv_list);

        ivLogout.setOnClickListener(this);
    }

    private void loadAccountData(UserAccountModel userAccountModelExtra) {
        if (userAccountModelExtra != null) {
            String name = userAccountModelExtra.getName();
            if (ValidationUtil.isValidString(name)) {
                tvClientName.setText(name);
            }
            String bankAccount = userAccountModelExtra.getBankAccount();
            String agency = userAccountModelExtra.getAgency();
            String accountInfo = FormatUtil.formatAccountNumber(bankAccount, agency);
            if (ValidationUtil.isValidString(accountInfo)) {
                tvAccount.setText(accountInfo);
            }
        }
    }

    private UserAccountModel getUserAccountModelExtra() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Parcelable parcelable = extras.getParcelable(EXTRA_USER_ACCOUNT);
            if (parcelable instanceof UserAccountModel)
                return (UserAccountModel) parcelable;
        }
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
