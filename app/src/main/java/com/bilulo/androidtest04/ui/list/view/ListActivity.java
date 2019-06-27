package com.bilulo.androidtest04.ui.list.view;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilulo.androidtest04.R;
import com.bilulo.androidtest04.common.BaseActivity;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.ui.list.configurator.ListConfigurator;
import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.router.ListRouter;
import com.bilulo.androidtest04.ui.list.view.adapter.StatementsAdapter;
import com.bilulo.androidtest04.utils.ValidationUtil;
import android.view.animation.AnimationUtils;

public class ListActivity extends BaseActivity implements ListContract.ActivityContract {
    public static final String EXTRA_USER_ACCOUNT = "extra-user-account";
    public ListContract.InteractorContract interactor;
    public ListRouter router;

    private TextView tvClientName;
    private ImageView ivLogout;
    private TextView tvAccount;
    private TextView tvBalance;
    private RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListConfigurator.configure(this);
        hideActionBar();
        findViews();
        configureRecyclerView();
        showProgressDialog();
        fetchAndLoadData();
    }

    public void fetchAndLoadData() {
        interactor.fetchAndLoadData(getUserAccountModelExtra());
    }

    private void configureRecyclerView() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_up_to_down);
        rvList.setLayoutAnimation(animationController);
    }

    private void findViews() {
        tvClientName = findViewById(R.id.tv_client_name);
        ivLogout = findViewById(R.id.iv_logout);
        tvAccount = findViewById(R.id.tv_account);
        tvBalance = findViewById(R.id.tv_balance);
        rvList = findViewById(R.id.rv_list);

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                router.startLoginActivity();
            }
        });
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
    public void loadUserAccountData(String name, String account, String balance) {
        if (ValidationUtil.isValidString(name))
            tvClientName.setText(name);
        if (ValidationUtil.isValidString(account))
            tvAccount.setText(account);
        if (ValidationUtil.isValidString(balance))
            tvBalance.setText(balance);
    }

    @Override
    public void loadStatements(StatementsAdapter adapter) {
        hideProgressDialog();
        rvList.setAdapter(adapter);
    }

    @Override
    public void displayError() {
        hideProgressDialog();
        showAlertDialog(getString(R.string.login_server_error));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        router.startLoginActivity();
    }
}
