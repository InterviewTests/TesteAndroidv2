package com.luizroseiro.testeandroidv2.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.adapters.RecentAdapter;
import com.luizroseiro.testeandroidv2.models.StatementData;
import com.luizroseiro.testeandroidv2.services.AppPreferences;
import com.luizroseiro.testeandroidv2.views.activities.MainActivity;

import java.util.List;

public class MainFragment extends Fragment {

    private Context context;
    private RecentAdapter recentAdapter;

    private TextView tvUserName;
    private TextView tvAccount;
    private TextView tvBalance;

    public MainFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        List<StatementData> statements = MainActivity.dataService.getStatementsList();

        tvUserName = view.findViewById(R.id.tv_user_name);
        tvAccount = view.findViewById(R.id.tv_account);
        tvBalance = view.findViewById(R.id.tv_balance);

        RecyclerView rvRecent = view.findViewById(R.id.rv_recent);
        rvRecent.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);

        recentAdapter = new RecentAdapter(statements);

        rvRecent.setLayoutManager(layoutManager);
        rvRecent.setAdapter(recentAdapter);

        MainActivity.dataService.getStatements(this);

        ImageView ivLogout = view.findViewById(R.id.iv_logout);
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dataService.logoutUser();
            }
        });

        setUserData();

        return view;

    }

    private void setUserData() {
        tvUserName.setText(AppPreferences.getUserName(getContext()));
        tvAccount.setText(context.getString(R.string.account_data,
                AppPreferences.getUserAgency(context), AppPreferences.getUserBankAccount(context)));
        tvBalance.setText(context.getString(R.string.balance_data,
                AppPreferences.getUserBalance(context)));
    }

    public void notifyAdapter() {
        recentAdapter.notifyDataSetChanged();
    }

}
