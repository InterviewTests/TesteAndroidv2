package com.luizroseiro.testeandroidv2.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.services.AppPreferences;
import com.luizroseiro.testeandroidv2.views.activities.MainActivity;

public class MainFragment extends Fragment {

    private Context context;

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

        tvUserName = view.findViewById(R.id.tv_user_name);
        tvAccount = view.findViewById(R.id.tv_account);
        tvBalance = view.findViewById(R.id.tv_balance);

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

}
